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
+ 로그인: FirebaseAuth와 SharedPreferences를 이용한 onCreate 메소드로 로그인 기능을 구현하고, 이메일과 비밀번호를 파라미터로 받아 Firebase를 통한 인증을 수행합니다.
signUp 함수는 이름, 이메일, 비밀번호를 파라미터로 받아 Firebase 인증을 통해 FirebaseDatabase에 저장하며, 성공 시 Toast 알림과 함께 Firebase 실시간 데이터베이스에 사용자 데이터를 추가합니다.

<table style="width: 100%;">
  <tr>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/5d73974f-6502-4767-aa40-6a332799db84" alt="Image 1" style="width: 100%;">
      <p>Login</p>
    </td>
    <td style="text-align: center;">
      <img src="https://github.com/gumchinjun/VeggieNeighbor/assets/97167373/c8312704-e39f-4899-bbca-158abb5294aa" alt="Image 2" style="width: 100%;">
      <p>Login</p>
  </tr>
</table>
