//SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.10;

contract Auction {
    address payable public highestBidder;
    uint public highestBid;

    constructor () public {
        highestBidder = msg.sender;
        highestBid = 0;
    }

    // 입찰처리 함수 (바로바로)
    function bid() public payable {
        //현재 입찰액이 최고 입찰액보다 높인지 확인
        require(msg.value > highestBid);
        //기존최고 입찰자에게 반환할 액수 설정
        uint refundAmount = highestBid;
        address currentHighestBidder = highestBidder;
        //스테이트값 업데이트
        highestBid = msg.value;
        //이전 최고액 입찰자에게 입찰금 반환
        highestBidder = msg.sender;

        if (!currentHighestBidder.send(refundAmount)) {
            revert();
        }

    }
}


