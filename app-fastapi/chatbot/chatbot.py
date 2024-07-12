from langchain_core.prompts import PromptTemplate
from chatbot import const
from langchain.chains import GraphCypherQAChain
from langchain_community.graphs import Neo4jGraph
from langchain_openai import ChatOpenAI

CYPHER_GENERATION_PROMPT = PromptTemplate(
    input_variables=["schema", "question"],
    template=const.CYPHER_GENERATION_TEMPLATE
)

CYPHER_QA_PROMPT = PromptTemplate(
    input_variables=["context", "question"],
    template=const.CYPHER_QA_TEMPLATE
)

graph = Neo4jGraph(
    **const.GRAPH_PARAMS
)

chain = GraphCypherQAChain.from_llm(
    llm=ChatOpenAI(temperature=0.3, model="gpt-4o"),
    graph=graph,
    verbose=True,
    cypher_prompt=CYPHER_GENERATION_PROMPT,
    qa_prompt=CYPHER_QA_PROMPT,
)

if __name__ == "__main__":
    """
    Chatbot 테스트
    """
    while True:
        question = input("질문을 입력하세요. 종료하려면 q를 입력하세요.: ")
        if question == "q":
            break
        answer = chain.invoke(question)
        print("답변:", answer["result"])
