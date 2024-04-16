import axios from 'axios';

export default {

  getCelestialObjects() {
    return axios.get(`/api/celestial-objects`)
  },

  getCelestialObjectById(celestialObjectId) {
    console.log('call to getCelestialObjectById with id: ' + celestialObjectId)
    return axios.get(`/api/celestial-objects/${celestialObjectId}`)
  },

  filterCelestialObjects(nameString, categoryArray) {
    let qs = '';
    // Add name to query string if passed in
    if (nameString) {
      qs += `name=${nameString}&`;
    }

    // Add categories to query string if passed in
    if (categoryArray && categoryArray.length > 0) {
      for (let category of categoryArray) {
        qs += `category=${category}&`;
      }
  }

    // both options leave a trailing '&'. Remove it.
    if (qs.length > 0) {
      qs = qs.slice (0, qs.length - 1); // note I added `qs =` at start
    }
    // trim() removes all whitespace
    if (qs.trim() === '') {
      return this.getCelestialObjects();
    } else {
      return axios.get(`/api/celestial-objects?${qs}`);
    }

  }
}
