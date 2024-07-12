# import chatbot.const as const
# from langchain_core.prompts import PromptTemplate
# from langchain_openai import ChatOpenAI
# from langchain.chains import GraphCypherQAChain
# from langchain_community.graphs import Neo4jGraph
# import uvicorn
# from fastapi import FastAPI, Request, HTTPException
# from fastapi.responses import JSONResponse
# from fastapi.middleware.cors import CORSMiddleware
# from dto import QuestionDto
# from chatbot import chain
# from preprocessing import synonym_replacement as sr
#
#
# cyper_prompt = PromptTemplate(**const.PROMPT_PARAMS)
# graph = Neo4jGraph(**const.GRAPH_PARAMS)
#
# gpt4_model = GraphCypherQAChain.from_llm(
#     ChatOpenAI(temperature=0, model="gpt-4o"),
#     graph=graph,
#     verbose=True,
#     cypher_prompt=cyper_prompt
# )
#
# gpt4_model.invoke("컴퓨터시스템공학과에 대해서 간단히 요약해줘")
#
# app = FastAPI()
#
# origins = [
#
# ]
# # CORS 설정
# app.add_middleware(
#     CORSMiddleware,
#     allow_origins=origins,  # 모든 도메인 허용, 필요시 특정 도메인으로 변경
#     allow_credentials=True,
#     allow_methods=["POST"],
#     allow_headers=["*"],
# )
#
#
# # 엔드포인트 제한을 검사하는 함수
# def check_endpoint(request: Request):
#     allowed_endpoints = ['gpt-entrance']  # 허용할 엔드포인트 목록
#     requested_endpoint = request.url.path.strip('/')
#
#     if requested_endpoint not in allowed_endpoints:
#         raise HTTPException(status_code=403, detail="Forbidden")
#
#
# @app.middleware("http")
# async def endpoint_checker_middleware(request: Request, call_next):
#     try:
#         check_endpoint(request)
#         response = await call_next(request)
#         return response
#     except HTTPException as e:
#         return JSONResponse(status_code=e.status_code, content={"error": e.detail})
#
#
# @app.post('/gpt-entrance')
# async def gpt_entrance(
#         request_data: QuestionDto
# ):
#     try:
#         data = request_data.content
#         print('질문 :', data)
#         data = sr(data)
#         print('유의어 대체 후 질문 :', data)
#         answer = chain.invoke(data)
#         chain.invoke()
#         print('답변 :', answer["result"])
#         return {"answer": answer["result"]}
#
#     except Exception as e:
#         print(f"예외 발생: {str(e)}")
#         return JSONResponse(status_code=500, content={"error": str(e)})
#
#
# if __name__ == '__main__':
#     uvicorn.run(app, host='0.0.0.0', port=8000)
