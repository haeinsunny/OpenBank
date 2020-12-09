App = {  //개체
	web3Provider: null,
	contract: null,
	init: async function() {  //펫정보를  화면에 뿌린다
		$.getJSON('./PetListServ', function(data) {    //'./pets.json' 나중에 서블릿만들어서 대체할거임
			var petsRow = $('#petsRow')
			var petTemplate = $('#petTemplate')  //이 영역의값을 복사해서 petsRows영역에도 append할거임
			for (i = 0; i < data.length; i++) {
				petTemplate.find('.panel-title').text(data[i].name);
				petTemplate.find('img').attr('src', data[i].picture);  //attr: img태그의 src속성값 바꿔줌
				petTemplate.find('.pet-breed').text(data[i].breed);
				petTemplate.find('.pet-age').text(data[i].age);
				petTemplate.find('.pet-location').text(data[i].location);
				petTemplate.find('.btn-adopt').attr('data-id', data[i].id);  //제이쿼리의 data함수 사용해봄 위의 attr속성값 바꾸는것과 동일
				petsRow.append(petTemplate.html());
			}
			return App.initWeb3();
		});
		//return await App.initWeb3();  //함수 실행이 끝나고나면 App.initWeb3()을 부를꺼임 => json연결로 불러오는 방식
	},
	initWeb3: async function() {  //메타마스크 Provider 연결한다
		if (window.ethereum) {
			App.web3Provider = window.ethereum;
			try {  //이더리움을 연결
				await window.ethereum.enable();
			} catch (error) {
				console.error("Used denied account access");
			}
		} else if (window.web3) {  //이더리움 없다면 메타마스크를 연결
			App.web3Provider = window.web3.currentProvider;
		} else {    //이더리움, 메타마스크 없다면 가나슈를 직접 연결
			App.web3Provider = new Web3.providers.HttpProvider("http://127.0.0.1:7545");
		}
		web3 = new Web3(App.web3Provider);
		return App.initContract();
	},
	initContract: function() { 	//컨트렉트 연결
		App.contract = new web3.eth.Contract([  //Contract()괄호안에 JSON 구조의 abi를 넣어준다
    {
      "constant": true,
      "inputs": [
        {
          "internalType": "uint256",
          "name": "",
          "type": "uint256"
        }
      ],
      "name": "adopters",
      "outputs": [
        {
          "internalType": "address",
          "name": "",
          "type": "address"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [
        {
          "internalType": "uint256",
          "name": "petId",
          "type": "uint256"
        }
      ],
      "name": "adopt",
      "outputs": [
        {
          "internalType": "uint256",
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": true,
      "stateMutability": "payable",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [],
      "name": "getAdopters",
      "outputs": [
        {
          "internalType": "address[16]",
          "name": "",
          "type": "address[16]"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    }
  ]);  
		App.contract.options.address = "0xD255443c613C1601585B4391CA7875c4CF7B1c7E";  //json의 주소 가져오기
		
		App.bindEvents();
		return App.markAdopted();
	},   
	bindEvents: function() {  //입양버튼 클릭시 분양, 비용을 전송
		$(document).on('click', '.btn-adopt', App.handleAdopt);  //그룹이벤트함수(부모태그에 이벤트르 걸면 전파됨): 부모.on(이벤트 종류, 이벤트 대상, 함수)
	 },   
	markAdopted: function(adopters, account) {  //분양여부 마크표시한다(분양되면 막음표시)
		App.contract.methods.getAdopters().call().then(function(adopters){
			for(i =0; i<adopters.length; i++){
				if(adopters[i] !== '0x0000000000000000000000000000000000000000'){  //0x뒤에 40바이트(40개)
				$('.panel-pet').eq(i).find('button')	
							   .text('Success').attr('disabled', true);
				}
			}
		});
		
	 },   
	handleAdopt: function(event) {   //초기화면 펫들의 분양여부 표시
		event.preventDefault();
		
		var petId = parseInt($(event.target).data('id'));
		var amount = prompt("보내실 금액을 입력해 주세요");
		
		web3.eth.getAccounts(function(error, accounts){
			var account = accounts[0];
			
			return App.contract.methods	
					  .adopt(petId)	
					  .send({from:account, value: web3.utils.toWei(amount, "ether")})
					  .then(function(result){
						return App.markAdopted();
					});
		}) 
	}  
}

$(function() {
	App.init();  //App개체의 함수호출
});