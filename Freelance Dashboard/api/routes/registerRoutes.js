/** 
 * Author: Deep Patel.
 * Created On: 2021-07-28.
 * Contains route information for Registration.
 */

const express = require('express');
const registerController = require('../controllers/registerController');

const registerRoute = express.Router();

registerRoute.post('/add', registerController.add);
registerRoute.post('/fatchUser', registerController.fatchUser);
registerRoute.post('/fetchUserById', registerController.fatchUserById);
module.exports = registerRoute;