<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="${isHtmlCompressionEnabled}" compressJavaScript="true"
               compressCss="true">
  <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <html>
  <head>
    <jsp:include page="../app/resource.jsp"/>

    <!-- Module CSS -->
    <link href="/static/restaurant/css/restaurant.css" rel="stylesheet"/>
  </head>
  <body>
  <div class="content">

    <!--customer search button-->
    <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
            data-target="#customersearch" data-backdrop="static">
      <i class="far fa-search"></i>
      <spring:message code="lbl.common.search"/>
    </button>

    <!--customer report button-->
    <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
            data-target="#foodReportModal" data-backdrop="static">
      <i class="far fa-analytics"></i>
      <spring:message code="lbl.common.reports"/>
    </button>

    <br></br>

    <!--Add Table-->

    <div id="table-container" class="row">
      <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
        <div class="card hover-shadow">
          <div class="add-card card-body d-flex justify-content-between" data-toggle="modal"
               data-target="#editTableModal" data-backdrop="static">
            <i class="far fa-plus hover-translate-y-n3"></i>
          </div>
        </div>
      </div>

      <!-- Table card 1-->

      <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
        <div class="card hover-shadow">
          <div class="card-body d-flex justify-content-between">
            <a href="#"
               class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder"
                   src="/static/restaurant/img/Table 1.jpg">
            </a>
            <div class="details vertical-middle">
              <span class="Table-name"><b>Tables</b></span><br>
              <span class="Tables">10</span><br>
              <span class="user-contact"></span><br>
              <span class="badge badge-success mt-3"> Available </span>

            </div>

          </div>

          <div class="card-footer">
            <div class="actions d-flex justify-content-between px-4">
              <a href="#" class="action-item hover-translate-y-n3"
                 data-toggle="modal"
                 data-target="#editTableModal" data-backdrop="static">
                <i class="far fa-edit"></i>
              </a>
              <a href="#" class="action-item text-danger hover-translate-y-n3"
                 data-toggle="modal"
                 data-target="#deletetable" data-backdrop="static">
                <i class="far fa-trash-alt"></i>
              </a>
            </div>
          </div>
        </div>
      </div>


      <!-- Table card 2-->

      <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
        <div class="card hover-shadow">
          <div class="card-body d-flex justify-content-between">
            <a href="#"
               class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder"
                   src="/static/restaurant/img/Dinning Room.jpg">
            </a>
            <div class="details vertical-middle">
              <span class="Table-name"><b> Dining Room </b></span><br>
              <span class="Tables"> 05 </span><br>
              <span class="user-contact"></span><br>
              <span class="badge badge-danger mt-3"> Reserved </span>

            </div>

          </div>

          <div class="card-footer">
            <div class="actions d-flex justify-content-between px-4">
              <a href="#" class="action-item hover-translate-y-n3"
                 data-toggle="modal"
                 data-target="#editTableModal" data-backdrop="static">
                <i class="far fa-edit"></i>
              </a>
              <a href="#" class="action-item text-danger hover-translate-y-n3" data-toggle="modal"
                 data-target="#deletetable" data-backdrop="static">
                <i class="far fa-trash-alt"></i>
              </a>
            </div>
          </div>
        </div>
      </div>

      <!-- Table card 3-->

      <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
        <div class="card hover-shadow">
          <div class="card-body d-flex justify-content-between">
            <a href="#"
               class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder"
                   src="/static/restaurant/img/Table 2.jpg">
            </a>
            <div class="details vertical-middle">
              <span class="Table-name"><b> Table </b></span><br>
              <span class="Tables"> 02 </span><br>
              <span class="user-contact"></span><br>
              <span class="badge badge-success mt-3"> Available </span>

            </div>

          </div>

          <div class="card-footer">
            <div class="actions d-flex justify-content-between px-4">
              <a href="#" class="action-item hover-translate-y-n3"
                 data-toggle="modal"
                 data-target="#editTableModal" data-backdrop="static">
                <i class="far fa-edit"></i>
              </a>
              <a href="#" class="action-item text-danger hover-translate-y-n3"
                 data-toggle="modal"
                 data-target="#deletetable" data-backdrop="static">
                <i class="far fa-trash-alt"></i>
              </a>
            </div>
          </div>
        </div>
      </div>


        <%--edit modal--%>
      <div id="editTableModal" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Add/Edit Table</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form:form method="POST" action="/app/restaurant" modelAttribute="restaurant"
                       enctype="multipart/form-data">
              <div class="modal-body">
                <input type="hidden" name="id">
                <div class="form-group">
                  <input name="number" id="text" placeholder="Number*"
                         type="number" class="form-control">
                </div>

                <div class="form-group">
                  <select class="selectpicker form-control" name="type.id"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Type*">
                    <option value="1">Table</option>
                    <option value="2">Dinning Room</option>

                  </select>

                  <br></br>

                  <select class="selectpicker form-control" name="status.id"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Status*">
                    <option value="1">Available</option>
                    <option value="2">Reserved</option>
                  </select>
                  <br></br>

                  <span class="mandatory-warn">*These fields are mandatory</span>
                </div>
              </div>
              <div class="modal-footer">
                <button id="save" type="button" class="btn btn-primary btn-round btn-sm">Save
                </button>
                <button type="reset" class="btn btn-danger btn-round btn-sm"
                        data-dismiss="modal">Cancel
                </button>
              </div>
            </form:form>
          </div>
        </div>
      </div>


        <%--search menu--%>

      <div id="searchModel" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Search </h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>

            <div class="modal-body">
              <div class="col-lg-6">
                <div class="form-group">
                  <input name="text" placeholder="Number" type="text"
                         class="form-control">
                </div>

                <br>

                <select class="selectpicker form-control"
                        data-hide-disabled="true"
                        data-live-search="true"
                        title="Type">
                  <option>Table</option>
                  <option>Dining Room</option>

                </select>
                <br></br>

                <select class="selectpicker form-control"
                        data-hide-disabled="true"
                        data-live-search="true"
                        title="Status">
                  <option>Available</option>
                  <option>Reserved</option>
                </select>
              </div>
            </div>
          </div>

          <div class="modal-footer">
            <button type="submit" class="btn btn-primary btn-round btn-sm">
              Search
            </button>
            <button type="button" class="btn btn-danger btn-round btn-sm"
                    data-dismiss="modal">Cancel
            </button>
          </div>

        </div>
      </div>
    </div>


    <!--delete Table-->

    <div id="deletetable" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Delete Table</h5>
            <button type="button" class="close" data-dis1miss="modal"
                    aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            Are you sure you want to delete Table?
          </div>

          <div class="modal-footer">
            <button type="submit" class="btn btn-primary btn-round btn-sm">
              Delete
            </button>
            <button type="button" class="btn btn-danger btn-round btn-sm"
                    data-dismiss="modal">Cancel
            </button>
          </div>

        </div>
      </div>
    </div>
  </div>
    <%-- pagination bar added--%>
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


    <%-- report --%>

  <div id="reportModal" class="modal fade" tabindex="-1" role="dialog"
       aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-md" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Restaurant Reports</h5>
          <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <form:form method="POST" action="/app/restaurant/report"
                   modelAttribute="criteria" enctype="multipart/form-data">
          <div class="modal-body">
            <div class="form-group">
              <select id="type" name="typeId"
                      class="selectpicker form-control"
                      data-hide-disabled="true"
                      data-live-search="true">
                <option disabled selected>Select Type</option>
                <c:forEach items="${types}" var="type">
                  <option value="${type.value}">${type.text}</option>
                </c:forEach>
              </select>
            </div>
            <div class="form-group">
              <select id="status" name="statusId"
                      class="selectpicker form-control"
                      data-hide-disabled="true"
                      data-live-search="true">
                <option disabled selected>Select Status</option>
                <c:forEach items="${statuses}" var="status">
                  <option value="${status.value}">${status.text}</option>
                </c:forEach>
              </select>
            </div>
          </div>

          <div class="modal-footer">
            <button id="generate" type="submit"
                    class="btn btn-primary btn-round btn-sm">
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
  </div>
  <jsp:include page="../app/nav.jsp"/>
  <!-- Module JS -->
  <script src="/static/restaurant/js/restaurant.js"></script>
  </body>
  </html>
</compress:html>