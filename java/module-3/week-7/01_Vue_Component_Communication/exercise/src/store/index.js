import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
      state: {
        books: [
          {
            title: "Kafka on the Shore",
            author: "Haruki Murakami",
            read: false,
            isbn: "9781784877989"
          },
          {
            title: "The Girl With All the Gifts",
            author: "M.R. Carey",
            read: true,
            isbn: "9780356500157"
          },
          {
            title: "The Old Man and the Sea",
            author: "Ernest Hemingway",
            read: true,
            isbn: "9780684830490"
          },
          {
            title: "Le Petit Prince",
            author: "Antoine de Saint-Exupéry",
            read: false,
            isbn: "9783125971400"
          },
        ],
      },
      mutations: {
        toggleBookRead(state, bookToToggle) {
          const index = state.books.findIndex(book => book.isbn === bookToToggle.isbn);
          if (index !== -1) {
            state.books[index].read = !state.books[index].read;
          }
        },
        addBook(state, newBook) {
          const bookExists = state.books.some(book => book.isbn === newBook.isbn);
          if (!bookExists) {
            state.books.push({...newBook});
          } else {
            alert("This book already exists");
          }
        },
      },
    actions: {},
    modules: {},
    // Strict should not be used in production code. It is used here as a
    // learning aid to warn you if state is modified without using a mutation.
    strict: process.env.NODE_ENV !== 'production'
  });
}
