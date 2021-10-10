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
    <link href="/static/banquethall/css/banquethall.css" rel="stylesheet"/>
  </head>
  <body>

  <div class="content">
    <!--<p>This is Banquet Hall content</p>
  -->
    <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
            data-target="#searchModel" data-backdrop="static">
      <i class="far fa-search"></i>
      <spring:message code="lbl.common.search"/>
    </button>
    <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
            data-target="#reportModal" data-backdrop="static">
      <i class="far fa-analytics"></i>
      <spring:message code="lbl.common.reports"/>
    </button>
      <%--<h1>HaShaN</h1>--%>


    <!--Add banquet hall-->

    <div id="banquet-hall-container" class="row">
      <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
        <div class="card hover-shadow">
          <div class="add-card card-body d-flex justify-content-between"
               data-toggle="modal"
               data-target="#editBanquetHallModal" data-backdrop="static">
            <i class="far fa-plus hover-translate-y-n3"></i>
          </div>
        </div>
      </div>

      <c:forEach items="${banquetHalls}" var="banquetHall">
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
          <div class="card hover-shadow">
            <div class="card-body d-flex justify-content-between">
              <a href="#"
                 class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                 data-bw="3px">
                <img alt="Image placeholder"
                     src="/static/banquethall/img/${banquetHall.id}.jpg">
              </a>
              <div class="details vertical-middle">
                <span class="hall-name"><b>${banquetHall.name}</b></span><br>
                <span class="Capacity">${banquetHall.capacity} Seat Capacity</span><br>
                <span class="user-contact"></span><br>
                <span class="badge badge-success mt-3">
                Available
              </span>
              </div>
            </div>
            <div class="card-footer">
              <div class="actions d-flex justify-content-between px-4">
                <a href="#" class="action-item hover-translate-y-n3"
                   data-toggle="modal"
                   data-target="#editBanquetHallModal" data-backdrop="static">
                  <i class="far fa-edit"></i>
                </a>
                <a href="#" class="action-item text-danger hover-translate-y-n3"
                   data-toggle="modal"
                   data-target="#deletehall" data-backdrop="static">
                  <i class="far fa-trash-alt"></i>
                </a>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      <div id="editBanquetHallModal" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Add/Edit Banquet Halls</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form:form method="POST" action="/app/banquet-hall/"
                       modelAttribute="banquetHall"
                       enctype="multipart/form-data">
              <div class="modal-body">
                <input type="hidden" name="id">
                <div class="form-group">
                  <input type="file" name="image" accept="image/*">
                </div>
                <div class="form-group">
                  <input name="name" id="text" placeholder="Hall Name*"
                         type="text"
                         class="form-control">
                </div>
                <div class="form-group">
                  <input name="capacity" id="number"
                         type="number" class="form-control"
                         placeholder="Capacity*">
                </div>
                <div class="form-group">
                  <select class="selectpicker form-control" name="type.id"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Type*">
                    <option value="1">Conference Hall</option>
                    <option value="2">Reception Hall</option>
                    <option value="3">Other</option>
                  </select>
                  <br></br>
                  <select class="selectpicker form-control" name="status.id"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Status*">
                    <option value="1">Available</option>
                    <option value="2">In Repair</option>
                  </select>
                  <br></br>

                  <span class="mandatory-warn">*These fields are mandatory</span>
                </div>
              </div>
              <div class="modal-footer">
                <button id="save" type="button"
                        class="btn btn-primary btn-round btn-sm">Save
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
              <h5 class="modal-title">Search Banquet Hall</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <div class="col-lg-6">
                <div class="form-group">
                  <input name="text" placeholder="Hall Name" type="text"
                         class="form-control">
                </div>
                <div class="form-group">
                  <input name="text" placeholder="Capacity" type="text"
                         class="form-control">
                  <br>
                  <select class="selectpicker form-control"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Type">
                    <option>Conference Hall</option>
                    <option>Other</option>

                  </select>
                  <br></br>
                  <select class="selectpicker form-control"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Status">
                    <option>Available</option>
                    <option>Reserved</option>
                    <option>In Repair</option>
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


      <!--delete hall-->

      <div id="deletehall" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Delete Banquet Hall</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              Are you sure you want to delete the banquet hall ?
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
        <%--Report menu--%>
      <div id="reportModal" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Banquet Hall Reports</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form:form method="POST" action="/app/banquet-hall/report"
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
    <script src="/static/banquethall/js/banquethall.js"></script>
  </body>
  </html>
</compress:html>