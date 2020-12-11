const Fundding = artifacts.require("Fundding");

module.exports = function (deployer) {
  deployer.deploy(Fundding, 1800, 20);  //Fundding 컨트랙트를 초기값을가지고 배포한다
};
