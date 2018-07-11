/**
 * @author Valieva Elina
 *
 * STATIONS:
 * getStation - get stations
 * deleteStation - remove station by name
 * getCoordinates - return latitude/longitude by station name use google.map.api
 * updateStation - update station name
 */

var getStation = function () {
    var urlSearching = "/station/all";
    return getRequest(urlSearching);
};

var deleteStation = function (name) {
    var urlSearching = "/station/delete/" + name;
    deleteRequest(urlSearching, "You delete station");
};

var getCoordinates = function (city) {
    var url = "https://maps.googleapis.com/maps/api/geocode/json?address=$" + city + "&key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes";
    $.getJSON(url, function (data) {
        alert(JSON.stringify(data));
        if (data.results.length) {
            var location = data.results[0];
            var latitude = JSON.stringify(location.geometry.location.lat);
            var longitude = JSON.stringify(location.geometry.location.lng);
            addStation(city, latitude, longitude);
        }
    });
};

var addStation = function (name, coordinatesX, coordinatesY) {
    swal({
        title: name,
        text: 'latitude = ' + coordinatesX + ', longitude = ' + coordinatesY,
        icon: 'info',
        showCancelButton: true,
        cancelButtonText: 'Wrong parameters ...',
        confirmButtonText: "It's OK!"
    }).then((result) => {
        if (result.value) {
            var stationDTO = {
                name: name,
                latitude: coordinatesX,
                longitude: coordinatesY
            };
            var urlSearching = "/station/add";
            postRequest(stationDTO, urlSearching, "You add new station", "success");
        }
    });
};

var updateStation = function (station) {

    var urlSearching = "/station/update";
    putRequest(station, urlSearching, "You edit station.");
}