import os
from pathlib import Path

class Config:
    # Ruta correcta ahora que los archivos están en su lugar
    BASE_DIR = Path(__file__).resolve().parent.parent
    WALLET_DIR = BASE_DIR / "app" / "Wallet_backendPython"
    
    user = os.getenv("DB_USER", "DEVELOPER_02")
    password = os.getenv("DB_PASS", "Admin12345678")
    dsn = os.getenv("DB_TNS_NAME", "backendpython_high")
    
    SQLALCHEMY_DATABASE_URI = (
        f"oracle+oracledb://{user}:{password}@{dsn}"
        f"?config_dir={WALLET_DIR}&wallet_location={WALLET_DIR}"
    )
    
    SQLALCHEMY_TRACK_MODIFICATIONS = False