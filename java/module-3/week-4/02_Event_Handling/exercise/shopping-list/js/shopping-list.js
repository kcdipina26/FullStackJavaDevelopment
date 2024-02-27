let allItemsIncomplete = true;
const pageTitle = 'My Shopping List';
const groceries = [
  { id: 1, name: 'Oatmeal', completed: false },
  { id: 2, name: 'Milk', completed: false },
  { id: 3, name: 'Banana', completed: false },
  { id: 4, name: 'Strawberries', completed: false },
  { id: 5, name: 'Lunch Meat', completed: false },
  { id: 6, name: 'Bread', completed: false },
  { id: 7, name: 'Grapes', completed: false },
  { id: 8, name: 'Steak', completed: false },
  { id: 9, name: 'Salad', completed: false },
  { id: 10, name: 'Tea', completed: false }
];


document.addEventListener('DOMContentLoaded', () => {
  setPageTitle();
  displayGroceries();
  attachEventListeners();
});

/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
function setPageTitle() {
  const title = document.getElementById('title');
  title.innerText = pageTitle;
}

/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() {
  const ul = document.querySelector('ul');
  groceries.forEach((item) => {
    const li = document.createElement('li');
    li.innerText = item.name;
    const checkCircle = document.createElement('i');
    checkCircle.setAttribute('class', 'far fa-check-circle');
    li.appendChild(checkCircle);
    ul.appendChild(li);
  });
}

document.addEventListener('DOMContentLoaded', () => {
  setPageTitle();
  displayGroceries();
  attachEventListeners();
});

function attachEventListeners() {
  const toggleAllBtn = document.getElementById('toggleAll');
  toggleAllBtn.addEventListener('click', toggleAllItems);

  const items = document.querySelectorAll('ul li');
  items.forEach(item => {
    item.addEventListener('click', markItemComplete);
    item.addEventListener('dblclick', markItemIncomplete);
  });
}
function toggleAllItems() {
  
  groceries.forEach(item => {
    item.completed = allItemsIncomplete; 
  });

  const items = document.querySelectorAll('ul li');
  items.forEach((item, index) => {
    if (allItemsIncomplete) {
      item.classList.add('completed');
      item.querySelector('i').classList.add('completed');
    } else {
      item.classList.remove('completed');
      item.querySelector('i').classList.remove('completed');
    }
  });

  allItemsIncomplete = !allItemsIncomplete;
  document.getElementById('toggleAll').innerText = allItemsIncomplete ? 'Mark All Complete' : 'Mark All Incomplete';
}

function markItemComplete(event) {
  const target = event.target;
  if (!target.classList.contains('completed')) {
    target.classList.add('completed');
    target.querySelector('i').classList.add('completed');
  }
}

function markItemIncomplete(event) {
  const target = event.target;
  if (target.classList.contains('completed')) {
    target.classList.remove('completed');
    target.querySelector('i').classList.remove('completed');
  }
}
