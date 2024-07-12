import os

GRAPH_PARAMS = {
    "url": os.environ["NEO4J_URI"],
    "username": os.environ["NEO4J_USERNAME"],
    "password": os.environ["NEO4J_PASSWORD"],
}

CYPHER_GENERATION_TEMPLATE = """
Task:그래프 데이터베이스를 쿼리하기 위해 Cypher 문을 생성합니다.

Instructions:
스키마에서 제공된 관계 유형 및 속성만 사용하세요.
제공되지 않은 다른 관계 유형이나 속성은 사용하지 마세요.
최단 경로를 찾습니다.

Schema:
{schema}

Note: 답변에 어떠한 설명이나 사과도 포함하지 마세요.
Cypher 문장을 작성하는 것 외에 다른 것을 묻는 질문에는 응답하지 마세요.
생성된 Cypher 문장을 제외한 어떠한 텍스트도 포함하지 마세요.

The question is:
{question}
"""

CYPHER_QA_TEMPLATE = """당신은 훌륭하고 인간이 이해할 수 있는 답변을 만드는 데 도움을 주는 조수입니다.
정보 부분에는 답변을 구성하는 데 사용해야 하는 제공된 정보가 포함되어 있습니다.
제공된 정보는 권위가 있으므로 이를 의심하거나 내부 지식을 사용하여 수정하려고 해서는 안 됩니다.
대답을 질문에 대한 응답으로 들리게 만드십시오. 주어진 정보를 바탕으로 결과를 도출했다고 언급하지 마십시오.
예는 다음과 같습니다.

Question: Neo4j 주식을 소유한 관리자는 누구입니까?
Context:[manager:CTL LLC, manager:JANE STREET GROUP LLC]
Helpful Answer: CTL LLC, JANE STREET GROUP LLC는 Neo4j 주식을 소유하고 있습니다.

답변을 생성할 때 이 예를 따르십시오.
제공된 정보가 비어 있으면 해당 정보를 가지고 있지 않아 답을 모른다고 하세요.

Information:
{context}

Question: 
{question}
Helpful Answer:"""