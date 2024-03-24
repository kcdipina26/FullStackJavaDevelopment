const baseURL = 'http://localhost:3000';

function listTopics() {
  return fetch(`${baseURL}/topics`)
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .catch(error => {
      console.error('There has been a problem with your fetch operation:', error);
      throw error; // Rethrowing the error so it can be caught by the calling function
    });
}

function getTopic(id) {
    return fetch(`${baseURL}/topics/${id}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
        throw error; // Rethrowing the error so it can be handled by the caller
      });
  }
export { listTopics, getTopic };
