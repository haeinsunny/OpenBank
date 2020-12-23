<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" 
integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	url= "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20201202";
	$.ajax(url,{
		//dataType : 'json',	
		success: function(response){
			console.log(response);			
			var list = response.boxOfficeResult.dailyBoxOfficeList;		
			for( m of list ){  //for(i=0; list.length; i++) list[i].movieNm
				$('#result').append( $("<p />").html(m.movieNm) ); //덮어씌우지않고 차례로 써내려갈때 append 해준다. 그냥 html하면 마지막 데이터만 나옴.	
				$('#result').append( $("<p />").addClass("mcd").html(m.movieCd) );
			},
					
		}
	});
	//영화정보조회
	$('#result').on("click", ".mcd", function(){  //클래스가 mcd인것을 클릭하면! 실행되는 함수
		var movieCd = $(this).html();	
		var url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=430156241533f1d058c603178cc3ca0e&movieCd=" + movieCd;
		$.ajax(url,{
			success: function(response){
				console.log(response);
				//영화 출연배우를 출력
				var info = response.movieInfoResult.movieInfo.actors;
				$('#actor').empty(); //이미 출력된 데이터 지워주기
				for(i of info){
				$('#actor').append($("<p/>").html(i.peopleNm));
				}
			}
		});
	})
	
	
	
});
</script>
</head>
<body>
박스오피스 순위
<div id="result">
<div id="actor"></div>
</div>

</body>
</html>