import uvicorn
from fastapi import FastAPI, Request, HTTPException, Response
from fastapi.middleware.cors import CORSMiddleware
from starlette.responses import JSONResponse

from dto import ChatDTO
from chatbot import chain
from preprocessing import synonym_replacement as sr
from config import logger

app = FastAPI()

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["POST"],
    allow_headers=["*"],
)


# 엔드포인트 제한을 검사하는 함수
def check_endpoint(
        request: Request
) -> None:
    """
    허용되지 않는 엔드포인트 요청을 거부하는 함수

    :param request: http request
    :return: None
    """
    allowed_endpoints = ['api/chat']  # 허용할 엔드포인트 목록
    requested_endpoint = request.url.path.lstrip('/')

    logger.info(f"요청 엔드포인트: {requested_endpoint}")

    if requested_endpoint not in allowed_endpoints:
        logger.error(f"허용되지 않는 요청입니다: {requested_endpoint}")
        raise HTTPException(status_code=403, detail="Forbidden")


@app.middleware("http")
async def endpoint_checker_middleware(
        request: Request,
        call_next: callable
) -> Response:
    """
    엔드포인트 제한을 검사하는 미들웨어

    :param request: http request
    :param call_next: 요청이 전달 될 다음 미들웨어
    :return: response
    """
    try:
        check_endpoint(request)
        response = await call_next(request)
        logger.info(f"Response: {response}")
        return response
    except HTTPException as e:
        logger.error(f"예외 발생: {str(e)}")
        return JSONResponse(status_code=e.status_code, content={"error": e.detail})


@app.post('/api/chat', response_model=ChatDTO)
async def chat(
        request_data: ChatDTO,
) -> ChatDTO | JSONResponse:
    """
    챗봇을 통해 질문에 대한 답변을 생성하는 엔드포인트

    :param request_data: ChatDTO
    :return: ChatDTO
    """
    try:
        data = request_data.content
        logger.info(f'질문: {data}')
        data = sr(data)
        logger.info(f'유의어 대체 후 질문: {data}')
        answer = chain.invoke(data)
        logger.info(f'답변: {answer["result"]}')
        return ChatDTO(content=answer["result"])

    except Exception as e:
        logger.error(f"예외 발생: {str(e)}")
        return JSONResponse(status_code=500, content=ChatDTO(content=str("답변 생성 중 오류가 발생했습니다. 다시 시도해주세요.")))


if __name__ == '__main__':
    uvicorn.run(app, host='0.0.0.0', port=8000)
