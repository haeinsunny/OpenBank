App = {
	web3Provider: null,
	contract: null,
	bidList: ["경매물품1", "경매물품2"],
	init: async function() {
		const table = document.getElementById('table1');

		for (let i = 0; i < App.bidList.length; i++) {
			const row = table.insertRow();  //행번호가없으면 제일 마지막에 추가됨. 행번호있으면 중간에 끼워짐
			const cell1 = row.insertCell(0);
			const cell2 = row.insertCell(1);
			const cell3 = row.insertCell(2);

			cell1.innerHTML = App.bidList[i];
			cell2.innerHTML = "name";
			cell3.innerHTML = "0";

			//상품목록
			$('#title').append($('<option>').html(App.bidList[i]));
		}

		return await App.initWeb3();
	},
	initWeb3: async function() {
		if (window.ethereum) {
			App.web3Provider = window.ethereum;
			try {
				await window.ethereum.enable();
			} catch (error) {
				console.error("Used denied account access");
			}
		} else if (window.web3) {
			App.web3Provider = window.web3.currentProvider;
		} else {
			App.web3Provider = new Web3.providers.HttpProvider("http://127.0.0.1:7545");
		}
		web3 = new Web3(App.web3Provider);

		return App.initContract();
	},
	initContract: function() {
		App.contract = new web3.eth.Contract(abi);
		App.contract.options.address = "0x228A2E33Dfb600AA80D098e648A22B6e824b2519";

		App.bindEvents();
		App.highbid();
		App.recentbid();
	},  //contract생성
	bindEvents: function() {  //입찰버튼 이벤트 불러오기
		$(document).on('click', '.btn', App.setBid);
	},
	highbid: function() {  //event
		App.contract.events.highestVoter({}, function(err, res) {  //최고입찰자가 나타나면 contract의 highestVoter이벤트를 발생시킴
			let i = 0;
			console.log(res);
			if (err) {
				console.log(i);
				return;
			}
			var htitle = decodeURIComponent(res.returnValues.htitle);
			var hAmount = web3.utils.toWei(res.returnValues.hAmount)/1000000000000000000;  //1. 입력할땐 eth이지만, 받아올땐 wei단위로 환산해줘야 인식한다.
			//2. wei단위로 인식한것을 다시 10의 18승으로 나누기 해줘야 입력한 eth단위로 표출된다.
			
			if (htitle == "경매물품1") { i = 1; }
			else if (htitle == "경매물품2") { i = 2; }

			const table = document.getElementById('table1');
			const row = table.rows[i];  //행번호가없으면 제일 마지막에 추가됨. 행번호있으면 중간에 끼워짐
			const cell2 = row.cells[1];
			const cell3 = row.cells[2];
			
			var hName = decodeURIComponent(res.returnValues.hName);  //데이터를 받은곳에서 디코딩
			cell2.innerHTML = hName;
			cell3.innerHTML = hAmount;
		})

	},
	recentbid: function() { //event
		App.contract.events.recentVoter({}, function(err, res) {  //최고입찰자가 나타나면 contract의 highestVoter이벤트를 발생시킴
			console.log(res);
			if (err) {
				console.log(i);
				return;
			}
			var rtitle = decodeURIComponent(res.returnValues.rtitle);
			var rName = decodeURIComponent(res.returnValues.rName);
			
			$('#rTitle').html(rtitle);
			$('#rFname').html(rName);
			$('#rAmount').html(res.returnValues.rAmount);
		})
	},
	setBid: function() {  //contract bid1 함수호출, 버튼 이벤트 핸들러
		event.preventDefault();

		var name = encodeURIComponent($('#name').val());  //데이터를 보내는 곳에서 인코딩
		var bid = parseInt($('#bid').val());
		var title = encodeURIComponent($('#title').val());

		web3.eth.getAccounts(function(error, accounts) {
			if (error) {
				console.log(error);
				return;
			}
			var account = accounts[0];  //첫번쨰 계정을 가져옴

			return App.contract.methods.bid1(title, name)
				.send({ from: account, value:  bid })
		.then(function(result) { console.log(result) });


		});
	}
  
}

$(function() {
	App.init();
});

	
















