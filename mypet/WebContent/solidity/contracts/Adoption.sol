pragma solidity ^0.5.0;

contract Adoption{
	address[16] public adopters;
	
	function adopt(uint petId) public payable returns(uint){  //분양할 펫 id를 넘겨주고있음. 분양하는 펫은 15마리
		require(petId >= 0 && petId <= 15);
		adopters[petId] = msg.sender;
		return petId;
		}
	
	function getAdopters() public view returns(address[16] memory){  //분양받은 사람들을 돌려줌
		return adopters;
	}	
}	
	