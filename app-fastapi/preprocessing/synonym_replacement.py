import json
import os

current_dir = os.path.dirname(__file__)
file_path = os.path.join(current_dir, 'data', 'synonym.json')

with open(file_path) as file:
    json_data = file.read()
dict_data = json.loads(json_data)


def synonym_replacement(
        sentence: str
) -> str:
    """
    문장에서 유의어를 대체하는 함수

    :param sentence: 문장
    :return: 대체된 문장
    """
    replaced_sentence = sentence
    for synonym in dict_data:
        if synonym in replaced_sentence:
            replaced_sentence = replaced_sentence.replace(synonym, dict_data[synonym])
    return replaced_sentence
