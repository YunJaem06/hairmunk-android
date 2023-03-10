# 헤어뭉크 🛒

## 프로젝트 설명

> 코로나로 인하여 직접 머리를 만지는 이용자가 많아졌고 미용재료를 판매하시는 어머니를 보고 아이디어가 떠올라 만들게된 미용재료 쇼핑몰입니다..<br/>

## 프로젝트 적용 기술

Kotlin, AAC ViewModel, Room ,DataBinding, LiveData, Coroutine, Navigation, firebase, Gson,
Retrofit2, OkHttp3, Glide

## 구현 사항

### 1. 로그인, 회원가입

Naver Login Api, Kakao Login Api를 이용하여 쉽게 회원가입 로그인이 가능합니다.<br/>
두 계정이 없다면 회원가입할 수 있는 창으로 옴길수 있습니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/224281469-412e141c-0384-49da-a883-9d0c4af752e8.png" width="40%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/224281503-f37cca40-99de-43e3-a37a-948f00d8bdba.png" width="40%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/224281530-2584d416-760f-4040-b36f-2c7c45a0a46c.png" width="40%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/224281550-dd04dfb6-6f4a-4768-92a7-9eb0c0a05243.png" width="40%" height="30%">
</p>
<br/>

### 2. 홈화면, 상품화면

OkHttp와 Retrofit을 이용하여 firebase와 통신합니다.<br/>
Recylerview를 사용한 상품 목록 볼 수 있습니다.<br/>
ViewPager를 이용하여 광고화면을 볼 수 있습니다.<br/>
상품 선택시 그 상품에 대한 상품상세 설명을 볼 수 있습니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/224282501-89780617-5703-462b-af2a-7568224d994c.png" width="40%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/224282546-089b58cf-5c91-4af5-8ac5-c3931de84785.png" width="40%" height="30%">
</p>
<br/>

### 3. 상품 카테고리

firebase 서버를 이용하여 미용제품에 대한 카테고리 화면등장, 큰 카테고리 안에 세부 카테고리와 상품을 볼수 있습니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/224283107-60f1251d-4404-4d1b-8398-8f2ad3efb590.png" width="40%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/224283121-3ade1b53-7bd4-46eb-b8c5-a6411ef1064d.png" width="40%" height="30%">
</p>
<br/>

### 4. 장바구니

Room db를 이용하여 장바구니 화면 구현
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/224283259-113d0b45-32e5-4c8c-bf31-fe2f564cd6dd.png" width="40%" height="30%">
<img src="https://user-images.githubusercontent.com/96619472/224283273-77133f17-ee7c-4183-a15c-2d95826f6f1d.png" width="40%" height="30%">
</p>
<br/>

### 5. 미용실 찾기

Kakao map Api와 Kakao search Api를 이용하여 미용실을 찾을수 있는 지도를 구현했습니다.
<p align="center">
    <img src="https://user-images.githubusercontent.com/96619472/221159891-4602e865-9b0b-4527-bb27-15006300639e.png" width="40%" height="30%">
</p>
<br/>

### 화면을 만들면서 배운것

* 기술을 하나씩 

