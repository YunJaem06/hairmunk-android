# 헤어뭉크 🛒

## 프로젝트 설명
> 코로나로 인하여 직접 머리를 만지는 이용자가 많아졌고 미용재료를 판매하시는 어머니를 보고 아이디어가 떠올라 만들게된 미용재료 쇼핑몰입니다..<br/>

## 프로젝트 적용 기술
Kotlin, AAC ViewModel, Room ,DataBinding, LiveData, Coroutine, Navigation, firebase, Gson, Retrofit2, OkHttp3, Glide

## 구현 사항

### 1. 로그인, 회원가입
Naver Login Api, Kakao Login Api를 이용하여 쉽게 회원가입 로그인이 가능합니다.<br/>
두 계정이 없다면 회원가입할 수 있는 창으로 옴길수 있습니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/221159891-4602e865-9b0b-4527-bb27-15006300639e.png" width="40%" height="30%">
</p>
<br/>

### 2. 홈화면, 상품화면
OkHttp와 Retrofit을 이용하여 서버와 통신합니다.<br/>
Recylerview를 사용한 목록 볼 수 있습니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/221159891-4602e865-9b0b-4527-bb27-15006300639e.png" width="40%" height="30%">
</p>
<br/>

### 3. 상품 카테고리
BottomSheetDialog를 이용하여 주소를 선택할 수 있습니다.<br/>
TextWatcher를 이용하여 포인트 사용 금액에 따른 총 결제금액이 변합니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/221159891-4602e865-9b0b-4527-bb27-15006300639e.png" width="40%" height="30%">
</p>
<br/>

### 4. 장바구니
갤러리 권한 확인을 확인합니다.<br/>
등록한 이미지는 FirebaseStorage에 저장되어 해당 주소를 서버에 저장합니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/221159891-4602e865-9b0b-4527-bb27-15006300639e.png" width="40%" height="30%">
</p>
<br/>

### 5. 미용실 찾기
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/221159891-4602e865-9b0b-4527-bb27-15006300639e.png" width="40%" height="30%">
</p>
<br/>

### 화면을 만들면서 배운것

* 코드를 리펙토링 하는 작업중 숫자가 버튼에 배치가 안되는 오류 발생 -> 실행 순서로 인한 오류 해결 완료
* 중복되는 코드 줄이며 1000줄 넘는 코드 400줄대로 리펙토링 완료
* thread가 바로 종료하는 바람에 마지막 번호 점수를 더하지 않고 끝나는 오류 발생 -> 1초 딜레이를 넣은후 종료하게 만듬
* Sharedpreference의 apply는 비동기적/ commit 은 동기적으로 처리되므로 commit 같은 경우 main 스레드에서 이용을 하면 스레드를 block 시키기
  때문에 문제를 일으 킬 수 있다는 것을 알았습니다.
* join()으로 스레드의 종료 순서를 조절할 수 있다는 것을 배웠습니다.

