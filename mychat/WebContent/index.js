App = {
	web3Provider: null,
	contract: null,
	init: async function() {  //버튼이벤트
		$(document).on('click', '.btn btn-primary', App.getChat); 
		return await App.initWeb3();
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
	initContract: function() {
		App.contract = new web3.eth.Contract([
			{
				"inputs": [],
				"name": "last_completed_migration",
				"outputs": [
					{
						"internalType": "uint256",
						"name": "",
						"type": "uint256"
					}
				],
				"stateMutability": "view",
				"type": "function",
				"constant": true
			},
			{
				"inputs": [],
				"name": "owner",
				"outputs": [
					{
						"internalType": "address",
						"name": "",
						"type": "address"
					}
				],
				"stateMutability": "view",
				"type": "function",
				"constant": true
			},
			{
				"inputs": [
					{
						"internalType": "uint256",
						"name": "completed",
						"type": "uint256"
					}
				],
				"name": "setCompleted",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			}
		]);
		App.contract.options.address = "0xba6840020033C7a746c3989cEa1736B0f57E6f35";
	},
	setChat: function() {
		contract.methods.setChat($('#name').val(), $('#chatting').val());
	},
	getChat: function() {
		contract.methods.chat().watch((err, res) => {
			//res출력
			$('#msg').text("asd");
		})
	},
}

$(function() {
	App.init();
});

















