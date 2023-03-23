$(document).ready(function() {
  $.ajax({
    url : "/ingredients?byType"
  }).then(function(data) {
	console.info(data);
    $.get('mustache/ingredients.mst', function(template) {
	  console.info(filterByType(data, 'WRAP'));
      $('#wraps').html(Mustache.render(template, filterByType(data, 'WRAP')));
      $('#proteins').html(Mustache.render(template, filterByType(data, 'PROTEIN')));
      $('#cheeses').html(Mustache.render(template, filterByType(data, 'CHEESE')));
      $('#veggies').html(Mustache.render(template, filterByType(data, 'VEGGIES')));
      $('#sauces').html(Mustache.render(template, filterByType(data, 'SAUCE')));
    });
  });
});

function filterByType(data, type) {
    return data.filter(item => item.type == type)
}