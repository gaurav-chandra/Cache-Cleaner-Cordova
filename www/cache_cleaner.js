var cache_cleaner = {
    delete: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'CacheCleaner', // mapped to our native Java class called "CalendarPlugin"
            'delete', // with this action name
            []
        ); 
    }
}
module.exports = cache_cleaner;