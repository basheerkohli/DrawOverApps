var exec = require('cordova/exec');

exports.checkPermission = function (arg0, success, error) {
    exec(success, error, 'DrawOverApps', 'checkPermission', [arg0]);
};
exports.requestPermission = function (arg0, success, error) {
    exec(success, error, 'DrawOverApps', 'requestPermission', [arg0]);
};

