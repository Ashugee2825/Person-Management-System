<style>
.sidenav {
  height: 100%;
  width: 200px;
  position: relative;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: white;
  overflow-x: hidden;
  padding-top: 20px;
}
.sidenav a {
  padding: 6px 6px 6px 32px;
  text-decoration: none;
  font-size: 15px;
  color: white;
  display: block;
  background-color: rgb(111, 28, 245);
  border-style: solid;
  border-color: white green blue white;
  border-width:1px;
   
}
.sidenav a:hover {
  
   background-color: #111;
}
</style>
   <div class="sidenav">
	<a href="personCntrl?page=personDashboard&opr=showAll&pageNo=0&limit=0">Dashboard</a>
  	<a href="personCntrl?page=personDashboard&opr=addNew&pageNo=0&limit=0">Add New</a>
  	<a href="personCntrl?page=searchPerson&opr=showNone&pageNo=0&limit=0">Search</a>
  	<a href="personCntrl?page=personDashboard&opr=showAll&pageNo=1&limit=5">Dashboard(Paging)</a>
  	<a href="personCntrl?page=personDashboard&opr=addNew&pageNo=1&limit=5">Add New(Paging)</a>
  	<a href="personCntrl?page=searchPerson&opr=showNone&pageNo=1&limit=5">Search(Paging)</a>
  	<a href="#">Reports</a>
  	<a href="personCntrl?page=personDashboard&opr=editNext&pageNo=0&limit=0">Save & Next</a>
  	<a href="personCntrl?page=personDashboard&opr=saveShowNext&pageNo=0&limit=0">Save & Show & Next</a>
	</div>
