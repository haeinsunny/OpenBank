//SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.10;

contract bidding {
    address payable public creator;  // 컨트랙트 소유자
    uint public Bidders;  //입찰자들 수
    
    //입찰자 구조체 
    struct Bidder{
        address payable addr;
    	uint bid;	    
    }   
	
    //입찰자 관리를 위한 매핑
    mapping (uint => Bidder) public bidList;  

    /* 최고 입찰자 */
    string h1Title;
    string h1Name;
    uint public h1Bid;
    
    string h2Title;
    string h2Name;
    uint public h2Bid;

    constructor() public{
        creator = msg.sender;
    }
    
    event recentVoter(
        string rtitle,
        string rName,
        uint rAmount
    );

    event highestVoter(
        string htitle,
        string hName,
        uint hAmount
    );

    function bid1(string memory _title, string memory _name) public payable {
        if (msg.value > h1Bid) {
            Bidder storage bid = bidList[Bidders++];
    		bid.addr = msg.sender;
    		bid.bid = msg.value;
    		
            h1Title = _title;
            h1Name = _name;
            h1Bid = msg.value;
            emit highestVoter(_title, _name, msg.value);
        }
        emit recentVoter(_title, _name, msg.value);
           
    }
    
    function bid2(string memory _title, string memory _name) public payable {
        if (msg.value > h2Bid) {
            h2Title = _title;
            h2Name = _name;
            h2Bid = msg.value;
            emit highestVoter(_title, _name, msg.value);
        }
        emit recentVoter(_title, _name, msg.value);
    }

    function getVoter() public view returns(string memory, string memory, uint) {
        return (h1Title, h1Name, h1Bid);
    }
    
    function checkSelfReached() public payable{
        uint i =0;
        for(i =0; i < Bidders; i++){
            if(bidList[i].addr == msg.sender){
                if(!msg.sender.send(bidList[i].bid)){
                    revert();  //throw
                }
                delete bidList[i];
                return;
            }
            
        }
        
    }
}