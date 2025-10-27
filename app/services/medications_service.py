from app.models.medications import Medication, db
from sqlalchemy.exc import IntegrityError

def listar_todos():
    return Medication.query.all()

def listar_por_id(id):
    return Medication.query.get(id)

def listar_por_estado(estado):
    return Medication.query.filter_by(medication_state=estado).all()

def crear(data):
    # Validar duplicado en batch_number antes de insertar
    if Medication.query.filter_by(batch_number=data.get("batch_number")).first():
        raise ValueError("El número de lote ya está registrado")

    medication = Medication(**data)
    db.session.add(medication)
    try:
        db.session.commit()
    except IntegrityError as e:
        db.session.rollback()
        raise ValueError(f"Error de integridad: {str(e)}")
    return medication

def editar(id, data):
    medication = Medication.query.get(id)
    if not medication:
        return None

    # Validar duplicado de batch_number si lo están modificando
    if "batch_number" in data and data["batch_number"] != medication.batch_number:
        if Medication.query.filter(
            Medication.batch_number == data["batch_number"],
            Medication.id != id
        ).first():
            raise ValueError("El número de lote ya está registrado")

    # Actualizar todos los campos recibidos en data
    for key, value in data.items():
        setattr(medication, key, value)

    try:
        db.session.commit()
    except IntegrityError as e:
        db.session.rollback()
        raise ValueError(f"Error de integridad: {str(e)}")

    return medication

def eliminar_logico(id):
    medication = Medication.query.get(id)
    if not medication:
        return None
    medication.medication_state = "I"  # I = Inactivo / Eliminado
    db.session.commit()
    return medication

def restaurar_logico(id):
    medication = Medication.query.get(id)
    if not medication:
        return None
    medication.medication_state = "A"  # A = Activo / Restaurado
    db.session.commit()
    return medication
