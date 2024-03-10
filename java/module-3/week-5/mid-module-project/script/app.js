/*
    app.js

*/

function generateCard(card) {
  let cardHTML = document.createElement('article');
  cardHTML.classList.add('product-card');
  const price = new Intl.NumberFormat(`en-US`, {
    currency: `USD`,
    style: "currency",
  }).format(card.price);
  const innerHTML = `
    <div class="sku-price">
      <div class="price">${price}</div>
    </div>
    <div class="product-name action" data-id="${card.productId}">${card.name}</div>
    <div class="product-image">
      <img src="${card.imageName}">
    </div>
    <div class="cart">
      <i class="fa-solid fa-cart-plus icon action" title="Add item to cart"></i>
    </div>
  `;
  cardHTML.innerHTML = innerHTML;
  return cardHTML;
}

function displayCards(filteredProducts = null) {
  let products = productService.getProducts();
  if (filteredProducts) {
    products = filteredProducts
  }
  
  let cardContainer = document.getElementById('product-cards');
  cardContainer.innerHTML = '';
  products.forEach(p => {
    let card = generateCard(p);
    cardContainer.appendChild(card);

    // Add event handlers for product name and cart icon
    const productName = card.querySelector('.product-name');
    productName.addEventListener("click", () => {
      alert(p.description);
    });

    const cartIcon = card.querySelector('.fa-cart-plus');
    cartIcon.addEventListener("click", () => {
      alert("Item added to the cart");
    });
  });
}
document.addEventListener("DOMContentLoaded", function () {
  displayCards(); // Display cards on page load

  // Event listener for product name clicks
  document.querySelectorAll('.product-name').forEach(productName => {
    productName.addEventListener('click', function (event) {
      const productId = event.currentTarget.getAttribute('data-id');
      const product = productService.getProductById(productId);
      showMessage(product.description);
    });
  });

  // Event listener for cart icon clicks
  document.querySelectorAll('.fa-cart-plus').forEach(cartIcon => {
    cartIcon.addEventListener('click', function () {
      showMessage('Item has been added to the cart.');
    });
  });

  // Function to display messages
  function showMessage(message) {
    const messageWindow = document.getElementById('message-window');
    messageWindow.textContent = message;
    messageWindow.classList.remove('hidden');
    setTimeout(() => {
      messageWindow.classList.add('hidden');
    }, 3000);
  }
});
