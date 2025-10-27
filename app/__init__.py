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

    
    from app.routes.medications_routes import medication_bp
    #from app.routes.rol_routes import rol_bp

    app.register_blueprint(medication_bp, url_prefix="/medications")
    #app.register_blueprint(rol_bp, url_prefix="/roles")

    return app