/**
 * Author: Deep Patel.
 * Created On: 2021-07-28
 * Controller for User Registration Controller.
 */

const registerModel = require('../models/registerModel');


const add = (req, res) => {
   const addUser = new registerModel();
   if (req.body && req.body.name) {
      addUser.Name = req.body.name;
   }
   if (req.body && req.body.mobile) {
      addUser.ContactNo = req.body.mobile;
   }
   if (req.body && req.body.email) {
      addUser.Email = req.body.email;
   }
   if (req.body && req.body.linkedin) {
      addUser.LinkedInProfile = req.body.linkedin;
   }
   if (req.body && req.body.website) {
      addUser.Website = req.body.website;
   }
   if (req.body && req.body.password) {
      addUser.Password = req.body.password;
   }
   addUser.save();

   return res.status(200).json({
      success: true,
      message: 'User added!',
      data: registerModel
   })
};

const fatchUser = (req, res) => {
   registerModel.findOne({ 'Email': req.body.email }, function (error, result) {
      res.send(result);
   });
};

const fatchUserById = (req, res) => {
   registerModel.findOne({ '_id': req.body.id }, function (error, result) {
      res.send(result);
   });
}
   module.exports = {
      add,
      fatchUser,
      fatchUserById
   }