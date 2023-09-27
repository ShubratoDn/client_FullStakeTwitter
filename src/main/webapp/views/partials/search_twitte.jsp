<!-- <div class="search_box"> -->
<!-- 	<input class="form-control my-2" type="text" name="search_twitte" placeholder="Search By twitte text">	 -->
<!-- 	<div id="twitte_search_result"> -->
<!-- 		<div class="rounded border p-2 mb-2 card"> -->
<!-- 			<p class="mb-0 pb-0 font-12 font-weight-bold border-success" >This is the post title</p> -->
<!-- 			<p class="mb-0 pb-0 font-10">This is the post This is the  This is the  This is the  This is the  This is the  This is the  </p> -->
<!-- 			<a href="#" class="stretched-link"></a> -->
<!-- 		</div> -->
<!-- 	</div>	 -->

<!-- </div> -->

<div class="search_box">
    <input id="search_twitte_input" class="form-control my-2" type="text" name="search_twitte" placeholder="Search By twitte text">    
    <div id="twitte_search_result"></div>
</div>



<script>
    document.getElementById('search_twitte_input').addEventListener('input', function () {    	
        const searchValue = this.value.trim();
        const twitteSearchResult = document.getElementById('twitte_search_result');

        if (searchValue.length > 0) {
        	let url = "/post/search/"+searchValue;
            // Send a GET request to the backend to fetch search results
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    // Clear previous search results
                    twitteSearchResult.innerHTML = '';

                    // Loop through the search results and create cards
                    data.forEach(post => {
                    	let title = post.title;
                    	let content = post.content;
                    	let link = "/post/"+post.id;
                        const cardDiv = document.createElement('div');
                        cardDiv.classList.add('rounded', 'border', 'p-2', 'mb-2', 'card');
                        cardDiv.innerHTML = `
                            <p class="mb-0 pb-0 font-12 font-weight-bold border-success">`+title+`</p>
                            <p class="mb-0 pb-0 font-10">`+content+`</p>
                            <a href="`+link+`" class="stretched-link"></a>
                        `;
                        twitteSearchResult.appendChild(cardDiv);
                    });
                })
                .catch(error => console.error('Error:', error));
        } else {
            // Clear the search results if the input is empty
            twitteSearchResult.innerHTML = '';
        }
    });
</script>
