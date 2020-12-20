# TwitchBoard | 트위치보드
> 시청자가 단합력을 보여 직접 해보는 게임!

## 소개(Introduce)
`깨고싶어 너와함께`라는 컨텐츠를 보고 바로 생각해낸 미니 유틸리티입니다!  
채팅창에서 메시지들을 불러와 가장 많이 투표된 키가 입력됩니다!

## 설치(Installation)

### 필요한 것 (Required)
[Java 8](https://java.com/)

### 개발자 (Developer)
1. 클론합니다. ```git clone https://github.com/playerdecuple/TwitchBoard```
2. IntelliJ IDEA에서 엽니다. 끝!

### 스트리머 (Streamer)
1. [릴리즈 페이지](https://github.com/playerdecuple/TwitchBoard/releases/tag/1.0.0)로 이동한 다음 `Assets` 목록에서 `Source code (zip)`을 클릭하여 다운로드 받아 주세요.  
2. 압축을 풀어 주세요.
3. [OAuth 키 발급 페이지](https://twitchapps.com/tmi/)로 가셔서 `Connect`를 누른 다음 로그인 해 주시고, 얻은 `OAuth` 키를 보관해 주세요. (해킹 사이트가 아닙니다.)
4. 압축이 풀려있는 폴더에서 `.config.txt`를 열어 주세요.
5. 이상한 영어들이 많습니다. 아래와 같은 형식일 겁니다.
```
STREAMER,[# + 스트리머 트위치 ID],[OAUTH 키],0
```
6. 스트리머 트위치 ID는 자신의 트위치 링크 (`https://twitch.tv/xxxxxxxx`, 트위치 메인 -> 자신의 프로필 클릭 -> 채널 클릭하면 나오는 곳의 링크주소)에서 `xxxxxxx` 부분입니다.
7. 형식대로 알맞게 수정해 줍니다. **중괄호는 넣지 마세요.** 아래는 예시입니다. 뒤에 `,0` 빼면 안 됩니다.
```
STREAMER,#playerdecuple,oauth:XXXXXXXXXXXXXXXXXXXX,0
```
8. 저장하고 나온 다음, 바로 해당 폴더에 있는 `실행.bat`을 더블클릭해서 엽니다. (바이러스나 랜섬웨어는 없습니다. 의심 되신다면 검사해 보세요.)
9. 끝! 만약 재연결한다는 메시지가 주르륵 뜬다면 껐다가 다시 켜 보세요. 그리고, 이 프로그램을 사용할 땐 넘락(`Num Lock`)을 꺼주세요! 그래야 정상작동됩니다.

## 패치노트 (Patch Note)
### 0.0.0
* 영문 키보드만 지원 (Shift와 같은 조합 키 X / Enter와 같은 유틸 키 X / 숫자 키 X / F1 ~ F12 X / Ins ~ PgDn X / 방향키 X)

# 중요사항
1. 본 레포지토리는 영리적 목적으로 **절대** 사용될 수 없습니다.
2. 이 소스 코드를 참고하거나 이용하여 새로운 소프트웨어를 만드신 경우 GPLv3 라이선스에 따라 오픈 소스로 공개하셔야 하며, 원작자(devKOR_decuple)를 표기해 주셔야 합니다.
