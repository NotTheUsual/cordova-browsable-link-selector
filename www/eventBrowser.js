var exec = require('cordova/exec');

exports.showBrowser = function(initialUrl, success, error) {
  exec(success, error, 'EventBrowser', 'showBrowser', [initialUrl]);
};
