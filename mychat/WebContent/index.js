App = {
	web3Provider: null,
	contract: null,
	init: async function() {  //버튼이벤트		 
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
		App.contract = new web3.eth.Contract(abi);
		App.contract.options.address = "0xABFD5C0B376d18A02aC57a94024Bf53fE57C68D2";
		$(document).on('click', '.btn', App.setChat);
		App.getChat();
	},
	setChat: function() {
		event.preventDefault();
		
		var name = encodeURIComponent($('#name').val());
		var msg = encodeURIComponent($('#chatting').val());
		
		web3.eth.getAccounts(function(error, accounts){
			if(error){
				console.log(error);
				return;
			}
			var account = accounts[0];  //첫번쨰 계정을 가져옴
			
			console.log(account);
			return App.contract.methods.setChat(name,msg)	
									   .send({from:account})	
									   .then(function(result){console.log(result)});
		})
	},
	getChat: function() {
		App.contract.events.chat({}, function(err, res) {
			console.log(res);	
			if(!err){
				let name = decodeURIComponent(res.returnValues.name);
				let msg = decodeURIComponent(res.returnValues.message);

				
				const div = document.createElement('div');
				div.className = 'card';
				const date = new Date(parseInt(res.returnValues.timeStamp)*1000); //솔리디티 시간은 *1000을해야 우리가 알아볼수있는 시간이 됨 =>정수변환>date객체에 넣으면 시간인식 ok!
				const string = `
				<h5 class= "card-header">${name}</h5>
				<div class="card-body">
				<h5 class="card-title">${msg}</h5>
				<p class="card-text">${date}</p>
				</div>
				`;
				div.innerHTML = string;
				$('#msg').append(div);				
			}
			else console.log(err);
		})
	}
}

$(function() {
	App.init();
});

















