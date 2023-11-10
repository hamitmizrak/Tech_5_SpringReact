import axios from 'axios';

// Const Persist Data
const REGISTER_URL="/register/api/v1.0.0";

// Services Api
class RegisterServicesApi  {
  

    // SPEED DATA
    // http://localhost:4444/register/api/v1.0.0/speed/data/5
    //@GetMapping("/speed/data/{id}")
     registerApiSpeedData(key) {
        return axios.get(REGISTER_URL+"/speed/data/"+key);
    }

    // ALL DELETE
    // http://localhost:4444/register/api/v1.0.0/delete/all
    //@GetMapping("/delete/all")
    registerApiDeleteAll() {
        return axios.get(REGISTER_URL+"/delete/all");
    }

    ////////////////////////////////////////////////////////////
    // C R U D
    // CREATE
    // http://localhost:4444/register/api/v1.0.0/create
    //@PostMapping("/create")
     registerApiCreate(registerDto) {
        return axios.post(`${REGISTER_URL}/create`,registerDto)
    }

    // LIST
    // http://localhost:4444/register/api/v1.0.0/list
    // @GetMapping("/list")
     registerApiList() {
        return axios.get(`${REGISTER_URL}/list`)
    }

    // FIND BY ID
    // http://localhost:4444/register/api/v1.0.0/find/1
   // @GetMapping(value="/find/{id}")
     registerApiFindById(id) {
        return axios.get(`${REGISTER_URL}/find/${id}`)
    }

    // FIND BY EMAIL ADDRESS
    // http://localhost:4444/register/api/v1.0.0/find/email/hamitmizrak@gmail.com
    //@GetMapping(value="/find/email/{address}")
     registerApiFindByEmailAddress(email) {
      return  axios.post(`${REGISTER_URL}/find/${email}`)
    }

    // UPDATE
    // http://localhost:4444/register/api/v1.0.0/update/1
    //@PutMapping(value="/update/{id}")
     registerApiUpdate(id,registerDto) {
      return axios.put(`${REGISTER_URL}/update/${id}`,registerDto)
    }

    // DELETE BY ID
    // http://localhost:4444/register/api/v1.0.0/delete/1
    //@DeleteMapping(value="/delete/{id}")
    registerApiDeleteById(id) {
        return axios.delete(`${REGISTER_URL}/delete/${id}`)
    }

} //end class

// EXPORT
export default new RegisterServicesApi();