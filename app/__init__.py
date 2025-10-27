from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from app.settings import Config

db = SQLAlchemy()
migrate = Migrate()

def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)
    
    db.init_app(app)
    migrate.init_app(app, db)

    # Importación de rutas
    from app.routes.medications_routes import medication_bp  # <-- DEVELOPER_02
    from app.routes.suppliers_routes import supplier_bp  # <-- DEVELOPER_01

    # Registro de blueprints
    app.register_blueprint(medication_bp, url_prefix="/medications")  # <-- DEVELOPER_02
    app.register_blueprint(supplier_bp, url_prefix="/suppliers")  # <-- DEVELOPER_01

    return app
