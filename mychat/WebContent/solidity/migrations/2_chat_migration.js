const ChattingContract = artifacts.require("ChattingContract");

module.exports = function (deployer) {
  deployer.deploy(ChattingContract);
};
