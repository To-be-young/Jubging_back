# Jupgging API DOCS

# DB Table

## User

| User | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 자동생성 id(PK) |
| user_id | String | 아이디 |
| password | String | 비밀번호 |
| phone_number | String | 핸드폰 번호 |
| distance | Double | 총 플로깅한 거리 |
| count | int | 플로깅횟수 |

## User_roles

| User_roles | 타입 | 설명 |
| --- | --- | --- |
| user_id | Long | id |
| user_roles | ROLE_USER | 유저 권한 |

## Plogging_records

- 플로깅 활동 기록 테이블

| Plogging_records | 타입 | 설명 |
| --- | --- | --- |
| recordId | Long | 기록번호 |
| distance | double | 거리 |
| date | LocalDateTime | 시간 |
| activity_time | String | 활동tlrks |
| user_id | String | 유저ID |

## pathway

- 경로기록 테이블
- plogging_records테이블의 recordId를 FK로 설정
- FK키와 time필드를를 복합키로 설정

| pathway | 타입 | 설명 |
| --- | --- | --- |
| plogging_records_record_id | plogging_records | plogging_records의 recordId를 FK로 설정 |
| time | LocalDateTime | 시간 |
| latitude | double | 위도 |
| longitude | double | 경도 |

## EmailValidationCode

- 이메일 인증 코드 테이블

| 이름 | 타입 | 설명 |
| --- | --- | --- |
| id | Long | 자동생성(PK) |
| email | String | 인증받을 이메일 |
| code | String | 인증용 코드 |
| create time | LocalDateTime | 인증요청시간 |

# API

## Sign

---

### Sign up(회원가입)

- 형식: Post
- url:

```powershell
api/sign/signup
```

### Request

```json
{
    "userId": "test123",
    "password": "pwd123",
		"nickname": "ko",
    "phoneNumber": "010-1234-5678",
}
```

### Response

```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "data": "test123"
}
```

---

### login (로그인)

- 형식: Post
- url

```powershell
api/sign/login
```

### Request

```json
{
    "userId": "test123",
    "password": "pwd123"
}
```

### Response

```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "data": {
        "grantType": "Bearer",
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2MDcxNDk0NCwiZXhwIjoxNjYwNzE4NTQ0fQ.7vUW8diljt1_rC78S5cUSxUN5SV79zeJWWTVyQOlACk",
        "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjE5MjQ1NDR9.YKShtMyXMxzfca8WgILKF6XfKywmBVkZMsZJFz7lJ1Y",
        "accessTokenExpireDate": 3600000
    }
}
```

---

## EmailSend(이메일 인증코드 요청)

- 이메일 인증용 발급
- 형식: Post

```powershell
api/sign/email
```

```json
{
	"email": "test@naver.com"
}
```

→ 실제 있는 이메일로 해주세요

```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "data": "send mail"
}
```

---

## EmailVerify(이메일 인증)

- 이메일 인증코드 Response
- 형식: Post

```powershell
api/sign/verifyCode
```

```jsx
{
	"email": "test@naver.com",
	"code": "123456"
}
```

→ 위에있는 send이메일로 보낸 코드를 써주시면 됩니다.

```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "data": "true"
}
```

---

## EmailRefresh(인증코드 재발급)

- 이메일 재발급
- 형식: Post

### Url

```json
/api/sign/refreshCode
```

### Request

```json
{
    "email": "[재발급 할 이메일]"
}
```

### Response

```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "data": "reissue Email"
}
```

---

## User


### user-emails/exists

- 이메일 중복 확인
- 형식 post
- url

```jsx
api/user/user-emails/exists
```

```json
api/user/user-emails/exists?email={email}
```

### user-nickname/exists

- 닉네임 중복 확인
- 형식 post
- url

```jsx
api/user/user-nickname/exists
```

```json
api/user/user-nickname/exists?nickname={nickname}
```

### getUserPloggingStatus

- 유저 플로깅 총거리, 횟수 확인
- 형식: get
- url

```powershell
api/user/userpage/plogging-status?userId={userId}
```

Response

```json
{
    "success": true,
    "code": 0,
    "msg": "성공하였습니다.",
    "data": {
        "count": 0,
        "distance": 0.0
    }
}
```

## plogging

---

### finish

- 플로깅 종료시 기록 저장
- 총 활동거리와 총 활동횟수 증가
- 형식: post
- url

```jsx
api/plogging/finish
```

```jsx
{
  "userId": "test123",
  "distance": 3.5,
  "activity_time": "1h",
  "pathway": [{
      "latitude": 35.123,
      "longitude": 36.234,
      "time": "2022-08-24-20:17:30"
    },
    {
      "latitude": 35.124,
      "longitude": 36.234,
      "time": "2022-08-24-20:17:31"
    },
    {
      "latitude": 35.125,
      "longitude": 36.234,
      "time": "2022-08-24-20:17:32"
    }
  ]
}
```

### log_finish

- 플로깅기록 조회

```json
api/plogging/log_list?userId={userId}
```
