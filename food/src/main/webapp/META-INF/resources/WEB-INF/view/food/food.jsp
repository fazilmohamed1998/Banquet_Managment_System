<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--food search--%>
<button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
        data-target="#foodSearchModal" data-backdrop="static">
  <i class="far fa-search"></i>
  <spring:message code="lbl.common.search"/>
</button>

<%--food report--%>
<button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
        data-target="#foodReportModal" data-backdrop="static">
  <i class="far fa-analytics"></i>
  <spring:message code="lbl.common.reports"/>
</button>
<div id="food-card-container" class="row">
  <%--Add food card--%>
  <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
    <div class="card hover-shadow">
      <div class="add-card card-body d-flex justify-content-between" data-toggle="modal"
           data-target="#foodEditModal" data-backdrop="static">
        <i class="far fa-plus hover-translate-y-n3"></i>
      </div>
    </div>
  </div>
  <c:forEach items="${foods}" var="food">
    <%--Food card 1 --%>
    <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
      <div class="card hover-shadow">
        <div class="card-body d-flex justify-content-between">
          <a href="#"
             class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
             data-bw="3px">
            <img alt="Image placeholder" src="/static/food/img/img_${food.id}.png">
          </a>
          <div class="details vertical-middle">
            <span class="food-name">${food.name}</span><br>
            <span class="food-price">${food.price}</span><br>
            <span class="food-id">00${food.id}</span><br>
            <span class="badge badge-success mt-3">
                ${food.category.name}</span>
            <span class="badge badge-success mt-3">
                ${food.cuisine.type}</span>
          </div>
        </div>
        <div class="card-footer">
          <div class="actions d-flex justify-content-between px-4">
            <a href="#" data-id="${food.id}" class="action-item hover-translate-y-n3" data-toggle="modal"
               data-target="#foodEditModal" data-backdrop="static">
              <i class="far fa-edit"></i>
            </a>
            <a href="#" data-id="${food.id}" class="action-item text-danger hover-translate-y-n3" data-toggle="modal"
               data-target="#deletefoodmodal" data-backdrop="static">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
      </div>
    </div>
  </c:forEach>
</div>

<div class="fixed-table-pagination" style="">
  <div class="float-left pagination-detail">
      <span class="pagination-info">
      Showing 1 to 10 of 800 rows
      </span><span class="page-list"><span class="btn-group dropdown dropup">
        <button class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown">
        <span class="page-size">
        10
        </span>
        <span class="caret"></span>
        </button>
        <div class="dropdown-menu"><a class="dropdown-item active" href="#">10</a><a
                class="dropdown-item " href="#">25</a><a class="dropdown-item " href="#">50</a><a
                class="dropdown-item " href="#">100</a><a class="dropdown-item "
                                                          href="#">All</a></div></span> rows per page</span>
  </div>
  <div class="float-right pagination">
    <ul class="pagination">
      <li class="page-item page-pre"><a class="page-link" aria-label="previous page"
                                        href="javascript:void(0)">&laquo;</a></li>
      <li class="page-item active"><a class="page-link" aria-label="to page 1"
                                      href="javascript:void(0)">1</a></li>
      <li class="page-item"><a class="page-link" aria-label="to page 2"
                               href="javascript:void(0)">2</a></li>
      <li class="page-item"><a class="page-link" aria-label="to page 3"
                               href="javascript:void(0)">3</a></li>
      <li class="page-item"><a class="page-link" aria-label="to page 4"
                               href="javascript:void(0)">4</a></li>
      <li class="page-item"><a class="page-link" aria-label="to page 5"
                               href="javascript:void(0)">5</a></li>
      <li class="page-item page-last-separator disabled"><a class="page-link" aria-label=""
                                                            href="javascript:void(0)">...</a>
      </li>
      <li class="page-item"><a class="page-link" aria-label="to page 80"
                               href="javascript:void(0)">80</a></li>
      <li class="page-item page-next"><a class="page-link" aria-label="next page"
                                         href="javascript:void(0)">&raquo;</a></li>
    </ul>
  </div>
</div>
<%--<nav>
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>--%>
<%--edit food--%>
<div id="foodEditModal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Add/Edit Food</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <%--form action is the uri which implemented in FoodController(Line 49:63)--%>
      <form:form method="POST" action="/app/food/" modelAttribute="food" enctype="multipart/form-data">
        <div class="modal-body">
          <input id="id" type="hidden" name="id">
          <div class="form-group">
            <input type="file" name="image" accept="image/*">
          </div>
          <div class="form-group">
              <%--name attribute is the key for the value when form submiting--%>
            <input id="name" type="text" name="name" placeholder="Name*" class="form-control">
          </div>
          <div class="form-group">
            <input id="price" type="number" name="price" class="form-control" placeholder="Price*">
          </div>
          <div class="form-group">
            <select id="category" name="category.id" class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true" title="Catergory*">
              <option value="1">Rice</option>
              <option value="2">Noodles</option>
              <option value="3">Pasta</option>
            </select>
            <br></br>
            <select id="cuisine" name="cuisine.id" class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true" title="Cuisine*">
              <option value="1">American</option>
              <option value="2">Chinese</option>
              <option value="3">Italian</option>
              <option value="4">Japanese</option>
              <option value="5">Add new....</option>
            </select>
            <br></br>
            <span class="mandatory-warn">*These fields are mandatory</span>
          </div>
        </div>

        <div class="modal-footer">
          <button id="save" type="button" class="btn btn-primary btn-round btn-sm">Save
          </button>
          <button id="cancel" type="reset" class="btn btn-danger btn-round btn-sm"
                  data-dismiss="modal">Cancel
          </button>
        </div>
      </form:form>
    </div>
  </div>
</div>

<%--delete food--%>
<div id="deletefoodmodal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Delete Food</h5>
        <button type="button" class="close" data-dismiss="modal"
                aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form:form action="/app/food/">
        <input type="hidden" id="delId">
        <div class="modal-body">
          Are you sure you want to delete the Food ?
        </div>
        <div class="modal-footer">
          <button id="delete" type="submit" class="btn btn-primary btn-round btn-sm">
            Delete
          </button>
          <button type="button" class="btn btn-danger btn-round btn-sm"
                  data-dismiss="modal">Cancel
          </button>
        </div>
      </form:form>
    </div>
  </div>
</div>

<%--Report menu--%>
<div id="foodReportModal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Food Reports</h5>
        <button type="button" class="close" data-dismiss="modal"
                aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form:form method="POST" action="/app/food/report" modelAttribute="criteria" data-parsley-validate=""
                 novalidate="">
        <div class="modal-body">
          <div class="form-group">
            <select name="categoryId" class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true" title="Catergory*">
              <option value="1">Rice</option>
              <option value="2">Noodles</option>
              <option value="3">Pasta</option>
            </select>
          </div>
          <div class="form-group">
            <select name="cuisineId" class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true" title="Cuisine*">
              <option value="1">American</option>
              <option value="2">Chinese</option>
              <option value="3">Italian</option>
              <option value="4">Japanese</option>
            </select>
          </div>
          <span class="mandatory-warn">*These fields are mandatory</span>
        </div>
        <div class="modal-footer">
          <button id="generate" type="submit" class="btn btn-primary btn-round btn-sm">
            Generate the Report
          </button>
          <button type="button" class="btn btn-danger btn-round btn-sm"
                  data-dismiss="modal">Cancel
          </button>
        </div>
      </form:form>
    </div>
  </div>
</div>


<%--search food--%>
<div id="foodSearchModal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Search Food</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="col-lg-6">
          <div class="form-group">
            <input name="text" placeholder="Name" type="text"
                   class="form-control">
          </div>
          <div class="form-group">
            <select class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true"
                    title="Catergory">
              <option>Corporate</option>
              <option>Wedding</option>
              <option>Birthday Party</option>
            </select>
            <br></br>
            <select class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true"
                    title="Cuisine">
              <option>American</option>
              <option>Chinese</option>
              <option>Italian</option>
              <option>Japanese</option>
              <option>Add new....</option>
            </select>
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <button type="submit" class="btn btn-primary btn-round btn-sm">Search
        </button>
        <button type="button" class="btn btn-danger btn-round btn-sm"
                data-dismiss="modal">Cancel
        </button>
      </div>
    </div>
  </div>
  <p>This is Food & Menu content</p>
  <script type="text/javascript">
      $(function () {
          $('.selectpicker').selectpicker();
      });

      $('#foodEditModal').on('show.bs.modal', function (e) {
          var $invoker = $(e.relatedTarget);
          var id = $invoker.data('id');
          if (id){
              ajaxGet('/app/food/' + $invoker.data('id'), false, false, function (response) {
                  $('#id').val(response.result.id);
                  $('#name').val(response.result.name);

                  $('#cuisine').selectpicker('val', response.result.cuisine.id);
                  $('#category').selectpicker('val', response.result.category.id);

                  var priceStr = response.result.price.toString();
                  if (!priceStr.includes('.')) {
                      priceStr += '.00';
                  }
                  $('#price').val(priceStr.replace('.', ''));
              });
          }
      }).on('hidden.bs.modal', function (e) {
          $(this).find('form:first').parsley().reset();
          $('#cancel').trigger('click');
      });

      /*by following code, food form will be submit to the backend when user clicks on save button*/
      $('#foodEditModal form #save').on('click', function (e) {
          e.preventDefault();
          ajaxPost($(this).parents('form:first'), null, function () {
              /*in here should validate required fields before submit the data to the server if there is any validation
              failure must return false; else should show progress bar to indicate to the user food saving in progress */
              showProgress();
          }, function (response) {
              /*after successful saving this method will trigger & here we hide the progress bar,
               also page should refresh with newly added food*/
              hideProgress();

              $('#foodEditModal').modal('hide');
              location.reload();
          });
      });

      $('#deletefoodmodal').on('show.bs.modal', function (e) {
          var $invoker = $(e.relatedTarget);
          var id = $invoker.data('id');
          $('#delId').val(id);
      });

      $('#deletefoodmodal form #delete').on('click', function (e) {
          e.preventDefault();
          var $form = $(this).parents('form:first');
          ajaxDelete($form.attr('action') + $('#delId').val(), function () {
              showProgress();
          }, function (response) {
              setTimeout(function () {
                  hideProgress();
              }, 35);
              $('#deletefoodmodal').modal('hide');
              location.reload();
          });
      });
  </script>
</div>