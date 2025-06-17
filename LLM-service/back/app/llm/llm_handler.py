from langchain_core.prompts import PromptTemplate
from transformers import pipeline, AutoModelForCausalLM, AutoTokenizer

from config import settings
from langchain_community.llms.huggingface_pipeline import HuggingFacePipeline
import asyncio
import torch

model_name = settings.llm_model

print("CUDA 버전:", torch.version.cuda)
print("GPU 사용 가능:", torch.cuda.is_available())
print("사용 중인 디바이스:", torch.cuda.get_device_name(0) if torch.cuda.is_available() else "None")
print("사용 중인 모델:", model_name)


model = AutoModelForCausalLM.from_pretrained(
    model_name,
    device_map="auto",  # 자동으로 GPU 활용
    torch_dtype=torch.float16 if torch.cuda.is_available() else torch.float32,
    trust_remote_code=True,
    revision="main"
)

tokenizer = AutoTokenizer.from_pretrained(settings.llm_model, use_fast=False, trust_remote_code=True)

pipe = pipeline(
    "text-generation",
    model=model,
    tokenizer=tokenizer,
    max_new_tokens=512,
    temperature=0.5,
    do_sample=True
)

llm = HuggingFacePipeline(pipeline=pipe)

whisky_prompt_template = PromptTemplate.from_template(
    """
    당신은 위스키와 술에 대해 잘 아는 전문가입니다.
    아래 예시처럼 답변해주세요.
    
    예시:
    질문: 조니워커 블루는 뭐야?
    답변: 조니워커 블루는 고급 스카치 위스키로, 부드럽고 풍부한 맛을 자랑합니다.
    
    질문: {question}
    답변:
"""
)

async def ask_llm(question: str) -> str:
    prompt = whisky_prompt_template.format(question=question)
    loop = asyncio.get_event_loop()
    response = await loop.run_in_executor(None, llm.invoke, prompt)
    return response