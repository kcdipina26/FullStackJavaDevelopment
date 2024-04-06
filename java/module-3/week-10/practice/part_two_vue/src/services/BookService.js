import axios from 'axios';

export default {

  getGenres() {
    return axios.get(`/api/books/genres`);
  },

  getBooks() {
    return axios.get(`/api/books`);
  },
  
  getBookById(bookId) {
    return axios.get(`/api/books/${bookId}`);
  },
  getAuthorDetails(authorId){              //requirement task5 //
    return axios.get(`/api/books/authors/${authorId}`);
  },

  filterBooks(titleString, genresArray) {
    let qs = '';
    // Add title to query string if passed in
    if (titleString){
        qs += `title=${titleString}&`;
    }
    // Add genres to query string if passed in
    if (genresArray && genresArray.length > 0) {
        for (let genre of genresArray) {
            qs += `genre=${genre}&`;
        }
    }

    // Both options above add a & at the end of the query string.
    // Remove the last trailing &
    if (qs.length > 0) {
        qs.slice(0, qs.length-1);
    }
    
    if (qs.trim() === '') {
        return this.getBooks();
    } else {
        return axios.get(`/api/books?${qs}`);
    }
  }

}
