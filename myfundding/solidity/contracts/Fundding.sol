//SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.10;

contract Fundding {

    // 투자자구조체 
    struct Investor {
        address payable addr; // 투자받는 어드레스     
        uint amount; // 투자액
    }

    address payable public creator; // 컨트랙트 소유자
    uint public numInvestors; // 투자자수	
    uint public deadline; // 마감일(UnixTime)	
    string public status; // 모금활동 스테이터스	
    bool public ended; // 모금 종료여부	
    uint public goalAmount; // 목표액	
    uint public totalAmount; // 총 투자액	
    mapping (uint => Investor) public investors; // 투자자 관리를 위한 매핑

    constructor (uint _duration, uint _goalAmount) public { // duration은 초단위로  60*30 => 1800초
        creator = msg.sender;
        deadline = now + _duration;
        goalAmount = _goalAmount;
        status = "Funding";
        ended = false; // false라서 모금 가동됨
        numInvestors = 0;
        totalAmount = 0;

    }


    // 투자 시에 호출되는 함수
    function fund() public payable {
        // 모금이 끝났다면 처리중단
        require(!ended);

        Investor storage inv = investors[numInvestors++]; // 투자자들 배열에 담음
        inv.addr = msg.sender; // 투자자의 정보를 담는다
        inv.amount = msg.value;
        totalAmount += inv.amount; // 모금된 총금액
    }

    // 모금 성공/실패 여부에 따라 송금
    function checkGoalReached() public onlyOwner { // creator만 이 함수를 실행할수 있음
    // 모금이 끝났다면 처리 중단
        require(!ended);

        // 마감날이 지나지 않았다면 처리 중단
        require(now >= deadline);

        if (totalAmount >= goalAmount) {
            status = "Campaign Succeeded :)";
            ended = true; // 빠져나가기
            // 컨트랙트 소유자에게 모금계좌에 있는 모든 이더를 송금한다
            if (!creator.send(address(this).balance)) {
                revert(); // throw
            }
        } else { // 목표액까지 모금 실패인 경우
            uint i = 0;
            status = "Campaign Failed :(";
            ended = true; // 빠져나가기
            // 각 투자자에게 투자금을 돌려줌
            while (i <= numInvestors) {
                if (!investors[i].addr.send(investors[i].amount)) {
                    revert();
                }
                i++;
            }
        }
    }

    // 이더반환만 하는 함수를 만든다
    // PULL형식으로 반환되는것이 안전(위는 PUSH 노안전)
    // 투자자들이 돈을 직접 찾아가는 방식이다
    function checkSelfReached() public payable {
        // 모금이 끝났다면 처리 중단
        require(!ended);

        // 마감날이 지나지 않았다면 처리 중단
        require(now >= deadline);

        uint i = 0;

        // 투자자 본인이 돈 가져감
        for (i=0; i < numInvestors; i++) {
            if (investors[i].addr == msg.sender) {
                if (!msg.sender.send(investors[i].amount)) {
                    revert(); // throw
                }
                delete investors[i];
                return;
            }

        }
    }


    modifier onlyOwner() { // creator만 함수를 수정할수있도록하는 메소드
        require(msg.sender == creator);
        _;
    }
}


