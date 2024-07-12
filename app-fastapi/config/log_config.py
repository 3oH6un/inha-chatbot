import logging

logging.basicConfig(
    filename='/app/logs/app-fastapi.log',  # 로그 파일 경로
    level=logging.INFO,  # 로그 레벨 설정
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'  # 로그 포맷 설정
)

logger = logging.getLogger(__name__)
