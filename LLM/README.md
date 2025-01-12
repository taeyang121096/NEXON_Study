# LLM & RAG Learning Plan

LLM (Large Language Models) 및 RAG (Retrieval-Augmented Generation) 학습 및 실무 적용 플랜

---

## **1. 기초 다지기 (1~2주)**

### **1.1 LLM 기본 개념 학습**
- **목표:** LLM의 원리와 구조를 이해하고 대표적인 모델의 활용 사례를 학습.
- **내용:**
  - Transformer 구조 이해
  - 대표적인 LLM (GPT, BERT, LLaMA 등)의 차이점과 활용 사례
  - 파인튜닝과 프롬프트 엔지니어링 개념 학습
- **자료 추천:**
  - YouTube 강의: [What are Transformers?](https://www.youtube.com/watch?v=4Bdc55j80l8)
  - 블로그: Hugging Face Blog 및 OpenAI Blog
  - 논문: Attention is All You Need (Transformers 논문)

### **1.2 RAG 기본 개념 학습**
- **목표:** RAG의 구성 요소와 동작 방식을 이해.
- **내용:**
  - RAG의 동작 원리 (LLM + 검색 시스템 통합)
  - 벡터 데이터베이스 (e.g., Pinecone, Weaviate, Elasticsearch)
  - 인덱싱과 검색의 기본
- **자료 추천:**
  - 문서: [Pinecone Docs](https://www.pinecone.io/docs/)
  - GitHub 프로젝트: [RAG example with OpenAI](https://github.com/openai/retrieval-augmented-generation)

---

## **2. 실습 환경 구축 및 간단한 프로젝트 진행 (3~4주)**

### **2.1 개발 환경 구축**
- **목표:** 실습에 필요한 환경을 설정.
- **내용:**
  - Python 학습: 데이터 처리 및 모델 활용을 위해 기본 문법 학습
  - Hugging Face 라이브러리 활용
  - Docker 및 REST API 학습 (Java와의 연동 대비)
- **자료 추천:**
  - Python: [Real Python](https://realpython.com/)
  - Docker: [Docker 공식 튜토리얼](https://docs.docker.com/get-started/)
  - Hugging Face: [Transformers 튜토리얼](https://huggingface.co/transformers/)

### **2.2 간단한 프로젝트 진행**
- **목표:** RAG의 기본 동작을 이해하기 위한 간단한 예제 구현.
- **프로젝트 아이디어:**
  - 주제: “FAQ 챗봇 만들기”
  - 기술 스택: 
    - LLM: OpenAI API 또는 Hugging Face 모델
    - RAG: Elasticsearch 또는 Pinecone
    - 데이터: 회사의 FAQ 데이터를 JSON 형식으로 준비
- **단계:**
  1. FAQ 데이터를 벡터로 변환 (Hugging Face 또는 다른 임베딩 모델 사용)
  2. 검색 시스템 설정 (Elasticsearch/Pinecone)
  3. 간단한 REST API로 챗봇 구현 (Spring Boot)

---

## **3. 고급 학습 및 서비스 개발 (5~6주)**

### **3.1 LLM 커스터마이징**
- **목표:** 파인튜닝 및 커스터마이징 기술 학습.
- **내용:**
  - Hugging Face Datasets와 Trainer API 활용
  - Transfer Learning: 기존 모델에 새 데이터 추가 학습
- **프로젝트 아이디어:** 회사 데이터 기반으로 LLM 커스터마이징

### **3.2 RAG 고급 활용**
- **목표:** RAG를 통해 대규모 데이터를 효율적으로 처리.
- **내용:**
  - 실시간 데이터 업데이트
  - 대규모 검색 성능 최적화
- **프로젝트 아이디어:** 사용자 로그 데이터를 활용한 실시간 추천 시스템

---

## **4. 실무 적용 프로젝트 (7~8주)**

### **4.1 회사 요구사항 분석**
- LLM과 RAG가 필요한 실무 문제 도출
- 주요 활용 사례: 
  - 고객 지원 자동화
  - 내부 데이터 검색 최적화
  - 데이터 요약 및 통찰 제공

### **4.2 POC(Proof of Concept) 개발**
- **목표:** 실무 환경에서 작동 가능한 작은 단위의 프로젝트 진행.
- **예:** 회사 문서 관리 시스템과 통합된 지능형 검색 엔진 개발
- **기술 스택:**
  - Java-Spring Boot 기반 백엔드
  - LLM/RAG 통합 (Python 또는 API 연동)

---

## **5. 학습과 병행할 참고자료**
1. **책**
   - [Deep Learning with Python](https://www.manning.com/books/deep-learning-with-python)
   - [Generative AI with Python and TensorFlow 2](https://www.packtpub.com/product/generative-ai-with-python-and-tensorflow-2/9781800565788)
2. **온라인 강좌**
   - [Coursera: Natural Language Processing Specialization](https://www.coursera.org/specializations/natural-language-processing)
   - [FastAI NLP 강의](https://course.fast.ai/)

---

## **결론**
1. 기초 개념을 빠르게 습득하고 실습 중심으로 학습.
2. 간단한 프로젝트를 통해 RAG의 동작 원리를 이해.
3. 실무 환경에서 적용 가능한 서비스 개발로 학습 내용을 강화.

## **마인드 맵**

![img.png](img.png)
