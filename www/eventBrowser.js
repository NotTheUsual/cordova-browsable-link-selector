var exec = require('cordova/exec');

exports.showBrowser = function(success, error) {
  exec(success, error, 'EventBrowser', 'showBrowser', []);
};
