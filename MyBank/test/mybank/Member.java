package mybank;

public class Member {

	String id;	
	String pw;
	
	public Member() {}  //생성자 선언 해줘야됨
	public Member(String id, String pw) {  //이런걸 만들면
		super();
		this.id = id;	
		this.pw = pw;
	}
	
	@Override  //Member class를 string으로 돌려준다 이거 안하면 콘솔에 외계값뜸
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + "]";
	}

}
