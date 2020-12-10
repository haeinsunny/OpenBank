pragma solidity ^0.6.10;

contract ChattingContract {
    string name;
    string message;
    uint timeStamp;
    
    event chat (
        string name, 
        string message,
        uint timeStamp);

   function setChat (string memory _name, string memory _msg) public {
       name = _name;
       message = _msg;
       timeStamp = now;  //솔리디티가  now로 현재시간 알려준다
       emit chat(_name, _msg, now);
   }
   
   function getChat() public view returns(string memory, string memory, uint) {
       return (name, message, timeStamp);
   }
   
}
