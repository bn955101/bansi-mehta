/**
 * Author: Janvi Patel.
 * Created On: 2021-07-20
 * Controller for Notification Controller.
 */

const notificationModel = require('../models/calendarModel');

/**
 * Method to get all the details of the clients.
 * @param {*} request
 * @param {*} response
 * @returns
 * find the all clients details and returns result as response
 */
module.exports.List = (req, response) => {

    var data = req['body'];
    let currentDate = data.currentDate;
    let userId = data.userId;
    notificationModel.find({'date': currentDate, 'userId': userId }, function(error, result)
    {
        if (error) {
            return response.status(400).json({
                result: [],
                message: error,
                success: false
            })
        }
        else {
            response.send(result)
        }
    });
}

module.exports.setStatus = (req, response) => {

    var data = req['body'];
    let currentDate = data.currentDate;
    let eventName = data.eventName;
    let category = data.category;
    notificationModel.find({'date': currentDate, 'eventName': eventName, 'category': category }, function(error, document) {

        let update = document[0]
        document[0].viewStatus = true;
        document[0].save(function(error){
            if (error) {
                return response.status(400).json({
                    result: [],
                    message: error,
                    success: false
                })
            } else {
                return response.status(200).json({
                    success: true
                })
            }
        })
    })
}