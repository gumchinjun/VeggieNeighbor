# ITM_Mobile Programming (코틀린 프로젝트)
1달동안 코틀린을 언어로 안드로이드 어플리케이션을 개발하는 프로젝트입니다.<br/>
저희 팀은 식자재를 직구 및 공구할 수 있고 인벤토리를 관리할 수 있는 어플리케이션을 개발하였습니다.

## Project-Team7 

## 1. 배경 및 주제 선정
+ 비용 상승으로 이어지는 긴 유통과정 때문에 신선도 저하로 이어질 수 있다는 단점이 있습니다.<br/> 
+ 또한, 농장에서 직접 구매하려면 종종 대량으로 구매해야 하므로 개별 가구에 부담이 될 수 있습니다
+ 따라서, 농장의 공동 구매를 용이하게 하는 앱으로, 이웃들이 대량 주문을 공유하고 비용을 절감하며 신선도와 저렴한 가격을 보장하고자 합니다. 


## 2. 주요 기능

### 1) 로그인 및 회원가입 
+ 로그인: FirebaseAuth와 SharedPreferences를 이용한 onCreate 메소드로 로그인 기능을 구현하고, 이메일과 비밀번호를 파라미터로 받아 Firebase를 통한 인증을 수행합니다.

<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/5d73974f-6502-4767-aa40-6a332799db84" alt="Image 1" style="width: 100%;">
      <p>Login</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/c8312704-e39f-4899-bbca-158abb5294aa" alt="Image 2" style="width: 100%;">
      <p>Login</p>
    </td>
  </tr>
</table>

### 2) 홈페이지
+ Navi 액티비티는 각 탭에 해당하는 프래그먼트로 구성되며, 바텀 네비게이션에서 아이콘과 텍스트를 정의하는 XML 메뉴 리소스를 생성합니다.
+ 네비게이션 컴포넌트와 통합하여 각 탭에 해당하는 프래그먼트나 목적지로 이동합니다.
+ Firebase Firestore에서 최근 GB(Group buying) 게시물을 검색하고, 각 문서에서 데이터를 추출하여 GB 리스트에 객체를 추가합니다.
+ 리사이클러 뷰를 통해 모든 GB 게시물 목록을 표시하며, 사용자는 각 항목을 클릭하여 자세한 내용을 볼 수 있습니다.
  
<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/96d3d81a-9aac-4956-b034-87a372d16071" alt="Image 3" style="width: 100%;">
      <p>홈페이지</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/6ac9102a-c35b-4e98-903a-bf2ed3fd2888" alt="Image 4" style="width: 100%;">
      <p>공동구매 상세페이지</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/1c258196-8b66-412d-ab54-9c9a79e902f0" alt="Image 5" style="width: 100%;">
      <p>상품페이지</p>
    </td>
  </tr>
</table>

### 3) 상품페이지
+ 농장에서 판매하는 신선한 상품을 카드 뷰로 표시, 사용자는 상품 상세를 볼 수 있습니다.
+ Firebase Firestore에서 상품 정보를 읽어와 리사이클러 뷰에 표시합니다.
+ 상품의 이미지는 Firebase Storage를 통해 관리되며, Glide 라이브러리로 불러와집니다.
+ 카테고리 선택 시 해당 상품만 필터링하여 보여주고, 상품 클릭 시 상세 페이지로 넘어갑니다.
  
<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/1c258196-8b66-412d-ab54-9c9a79e902f0" alt="Image 6" style="width: 100%;">
      <p>상품페이지(과일)</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/973bc1c3-2b95-4d91-aa47-7522ce68e349" alt="Image 7" style="width: 100%;">
      <p>상품페이지(야채)</p>
    </td>
  </tr>
</table>

### 4) 공동구매
+ 사용자가 '공동 구매 가능' 버튼을 클릭하면, 현재 선택된 제품에 대한 진행 중인 모든 공동 구매를 보여주는 액티비티(available GB)로 이동합니다.
+ 공동구매 게시글을 올리고 싶은 경우, 관련 게시물을 업로드 할 수 있습니다.
+ 이전 액티비티에서 받은 정보(제품의 가격)를 사용하여 사용자가 참여자 수를 입력하고 '가격 계산' 버튼을 클릭하면, 인당 가격이 자동으로 계산되어 적용됩니다.

<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/e9620f03-0d08-44bd-8220-3ab76ea45d1c" alt="Image 8" style="width: 100%;">
      <p>상품페이지</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/29aafd42-cdfd-4476-a277-a55ff24780fa" alt="Image 9" style="width: 100%;">
      <p>현재 가능한 공동구매 목록</p>
    </td>
        <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/1d37b64d-e8b9-4edc-ab6c-aebdc992ffdc" alt="Image 10" style="width: 100%;">
      <p>공동구매 폼</p>
    </td>
  </tr>
</table>

### 5) 냉장고(장바구니)
+ 영수증 OCR을 통해 '내 냉장고에 추가' 항목을 구현하고, Google의 MLKIT 텍스트 인식을 사용합니다.
+ OCR은 이미지에서 텍스트를 인식하여 "제품", "가격", "날짜"와 같은 패턴을 사용하여 필요한 정보를 추출합니다.
+ 사용자가 '내 냉장고에 추가'를 클릭하면 인식된 데이터를 파싱하여 Firestore에 냉장고 항목 데이터로 추가하고 유통 기한을 계산한 후 '내 냉장고'로 전환합니다.

<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/57bd293a-186f-4b6c-bc87-aad4e93c2a56" alt="Image 11" style="width: 100%;">
      <p>My Fridge 기본화면</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/aa01b1e6-e29e-466b-8463-b65ba7198b4d" alt="Image 12" style="width: 100%;">
      <p>OCR을 위한 업로드</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/7fd5d7d5-8696-432b-b17b-c909462f91e9" alt="Image 13" style="width: 100%;">
      <p>사진 업로드</p>
    </td>
  </tr>
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/c4b09d1a-47a9-4df3-828e-fa7fded52a8b" alt="Image 14" style="width: 100%;">
      <p>OCR 인식 결과</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/3edaa379-7c88-44c5-9b7d-5d56d2bd7f03" alt="Image 15" style="width: 100%;">
      <p>새로운 상품이 추가된 화면</p>
    </td>
  </tr>
</table>

### 6) 챗봇서비스(OpenAI)
+ 사용자가 아이템 선택에 도움을 요청할 때 도움을 주는 AI 기반 챗봇 서비스입니다.
+ `GPTActivity` 화면에서 사용자가 질문을 입력하고 'Send' 버튼을 누릅니다.
+ 질문은 GPT API로 전송되어 적절한 답변을 생성합니다.
+ 
<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/0292a095-216e-493d-b6ab-2b21c9aaf6c2" alt="Image 16" style="width: 100%;">
      <p> 챗봇 </p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/0b80c8aa-e84c-4ce0-8b00-1c2b66d0117f" alt="Image 17" style="width: 100%;">
      <p> 챗봇 </p>
    </td>
  </tr>
</table>

### 7) Firebase Cloud Messaging (알림 기능)
**캘린더 기능**
+ 사용자는 안드로이드 '캘린더 액티비티' 화면에서 날짜를 선택하고 일정을 저장합니다.
+ '저장' 버튼을 클릭하면 선택한 날짜에 일정이 저장되고 Firestore에 타임스탬프와 함께 업로드됩니다.
+ 저장된 일정은 수정하거나 삭제할 수 있으며, 앱의 이전 화면으로 돌아가는 '뒤로' 버튼이 제공됩니다.

**FCM**
+ 서버에서 클라이언트로 알림 메시지를 보내는 메시지 전송 서비스입니다. 앱 사용자의 배송 날짜에 관련 메세지를 전송할 수 있습니다.

- Node.js (서버 사이드)
1. Firebase Admin SDK 초기화 및 인증: 서버가 Firebase 서비스(Firestore, FCM)에 안전하게 접근할 수 있도록 합니다.
2. Firestore 데이터 처리: 필요한 데이터를 Firestore 데이터베이스에서 조회합니다.
3. FCM 푸시 알림 생성 및 전송: 필요한 정보를 바탕으로 FCM 푸시 알림을 설정하고 FCM을 통해 디바이스로 전송합니다.

- Kotlin (안드로이드 클라이언트 사이드)
1. FCM 메시지 수신: FCM으로부터 알림이 도착하면, 서비스가 알림의 내용을 파싱하여 필요한 경우 사용자에게 보여줄 데이터로 변환합니다.
2. UI에서 알림 표시: 받은 알림 데이터를 바탕으로 사용자에게 시각적으로 헤드업 알림을 표시합니다.

<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/c81ac6ae-eb98-47c4-93f4-e0348543b351" alt="Image 18" style="width: 100%;">
      <p>캘린더</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/a6e2ca2b-3c43-4638-83d1-28a3decab6fa" alt="Image 19" style="width: 100%;">
      <p>헤드업 메세지</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/fb97fe78-253b-4017-83e4-6a5168c5c1f6" alt="Image 20" style="width: 100%;">
      <p>메세지</p>
    </td>
  </tr>
</table>

<img width="500" alt="image" src="https://github.com/gumchinjun/gumchinjun.github.io/assets/97167373/a5b2de6b-04af-4d01-9b5e-302c53a5d1de">
