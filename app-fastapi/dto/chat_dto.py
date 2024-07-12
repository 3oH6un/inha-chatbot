from pydantic import BaseModel


class ChatDTO(BaseModel):
    """
    ChatDTO: spring boot 서버와의 통신을 위한 데이터 전송 객체 (DTO)
    """
    content: str
