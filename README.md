# ITM_Mobile Programming (코틀린 프로젝트)
1달동안 코틀린을 언어로 안드로이드 어플리케이션을 개발하는 프로젝트입니다.<br/>
저희 팀은 식자재를 직구 및 공구할 수 있고 인벤토리를 관리할 수 있는 어플리케이션을 개발하였습니다.

## Project-Team7 

## 1. 배경 및 주제 선정
+ 비용 상승으로 이어지는 긴 유통과정 때문에 신선도 저하로 이어질 수 있다는 단점이 있습니다.<br/> 
+ 또한, 농장에서 직접 구매하려면 종종 대량으로 구매해야 하므로 개별 가구에 부담이 될 수 있습니다
+ 따라서, 농장의 공동 구매를 용이하게 하는 앱으로, 이웃들이 대량 주문을 공유하고 비용을 절감하며 신선도와 저렴한 가격을 보장하고자 합니다. 


## 2. 주요 기능

### 1) 로그인
+ 로그인: 설명쓰기
<img width="138" alt="로그인1" src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/5d73974f-6502-4767-aa40-6a332799db84">
<img width="138" alt="로그인2" src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/c8312704-e39f-4899-bbca-158abb5294aa">


- DeepLearning
    - Chatbot service incorporates a Flask server, Langchain and OpenAI technologies. Below are the details of the technologies used:
    # Flask Server
    - The Flask server is designed to handle HTTP requests and responses between the client and server through the implementation of REST APIs.
    - Flask acts as the backend for the chatbot service, receiving questions from users, processing them through appropriate logic, and then returning responses.
    # Langchain & OpenAI
    - A toolkit for building NLP pipelines, enabling seamless integration of data loading, processing, search, and generation tasks into a unified process.
    - Retrieval-Augmented Generation (RAG): Develop search-based methods to find highly relevant information for a given query—specifically targeting facilities for people with disabilities from a CSV dataset, using              ChromaDB for efficient data embedding in vector space.
    - The answers are generated using OpenAI's gpt-3.5-turbo model, based on the results retrieved.
