
1. lombok 사용법
    - Lombok Plugin 설치
    - pom.xml에 dependancy 추가
    - setting에서 compiler -> annotation processor -> enable annotation processing 활성화

2. StreamExamples3
    - Integer.of 는 IntegerCache를 가지고 비교 하기 때문에 int i == 3 을 비교 하는것과 같다 IntegerCache의 high 는 127 이므로 127 이상의 값을 비교 할때는 equals 를 사용 해야 한다.


3. StreamExamples5ParallePerformancePractical
    - ArrayList : 초기에 length가 10인 array를 내부적으로 생성해서
                  add 메서드 사용시 용량 확인을 해서 다 찰때 마다 1.5배를 한다.
                  즉, array.length = 100,000 인데, 이게 꽉차면 그후에 element 1개만
                  더 추가를 해도 ArrayList의 내부크기는
                  100,000 * 1.5 = 150,000 (즉, array length : 100,000 => 150,000)
                 
                 
4. closure
	- this 접근 차이
	- method 접근 차이
