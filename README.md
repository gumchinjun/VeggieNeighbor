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
