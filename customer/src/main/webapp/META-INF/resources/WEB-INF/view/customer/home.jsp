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
        <link href="/static/customer/css/customer.css" rel="stylesheet"/>
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


        <!--add customer button-->
        <div id="table-container" class="row">
            <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
                <div class="card hover-shadow">
                    <div class="add-card card-body d-flex justify-content-between" data-toggle="modal"
                         data-target="#addcustomer" data-backdrop="static">
                        <i class="far fa-plus hover-translate-y-n3"></i>
                    </div>
                </div>
            </div>


            <!-- add Customer-->

            <div id="addcustomer" class="modal fade" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-md" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Add Customer Details</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                      <form:form method="POST" action="/app/customer" modelAttribute="customer"
                                 enctype="multipart/form-data">
                            <div class="modal-body">
                                <input type="hidden" name="id">
                                <div class="form-group">
                                    <input type="file" name="image" accept="image/*">
                                </div>
                                <div class="form-group">
                                  <input name="name" id="addname" placeholder="Customer Name*"
                                         type="text"
                                         class="form-control">
                                </div>
                                <div class="form-group">
                                  <input name="address" id="addAddress" placeholder="Customer Address*"
                                         type="text"
                                         class="form-control">
                                </div>
                                <div class="form-group">
                                  <input name="email" id="addEmail" placeholder="Customer Email*"
                                         type="email"
                                         class="form-control">
                                </div>
                                <div class="form-group">
                                  <input name="contactList[0].contactNumber" id="addMobileNumber"
                                         type="number" class="form-control"
                                         placeholder="Mobile Number*">
                                </div>
                                <div class="form-group">
                                  <input name="nic" id="addid"
                                         type="text" class="form-control"
                                         placeholder="ID Number*">
                                </div>
                                <div>
                                  <select class="selectpicker form-control" name="type.id"
                                          data-hide-disabled="true"
                                          data-live-search="true"
                                          title="Type*">
                                        <option value="1">Private Customer</option>
                                        <option value="2">Co-Operative Customer</option>
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



            <!-- member 1-->

            <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
                <div class="card hover-shadow">
                    <div class="card-body d-flex justify-content-between">
                        <a href="#"
                           class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                           data-bw="3px">
                            <img alt="Image placeholder"
                                 src="/static/customer/img/yasu.jpg">
                        </a>
                        <div class="details vertical-middle">
                            <span class="customername"><b>M P Dinakshi Jayasooriya</b></span><br>
                            <span class="email">dinujayaso11@gmail.com</span><br>
                            <span class="contact">0774561789</span><br>
                            <span class="badge badge-success mt-3">Private Customer</span>
                            <span class="badge badge-success mt-3"></span>

                        </div>

                    </div>

                    <div class="card-footer">
                        <div class="actions d-flex justify-content-between px-4">
                            <a href="#" class="action-item hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#editcustomer" data-backdrop="static">
                                <i class="far fa-edit"></i>
                            </a>
                            <a href="#" class="action-item text-danger hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#deletecustomer" data-backdrop="static">
                                <i class="far fa-trash-alt"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!--member 2-->

            <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
                <div class="card hover-shadow">
                    <div class="card-body d-flex justify-content-between">
                        <a href="#"
                           class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                           data-bw="3px">
                            <img alt="Image placeholder"
                                 src="/static/customer/img/gayan.jpg">
                        </a>
                        <div class="details vertical-middle">
                            <span class="customername"><b>P D G Pradeep</b></span><br>
                            <span class="email">gayanpj1996@gmail.com</span><br>
                            <span class="contact">0775371217</span><br>
                            <span class="badge badge-success mt-3">Private Customer</span>
                            <span class="badge badge-success mt-3"></span>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="actions d-flex justify-content-between px-4">
                            <a href="#" class="action-item hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#editcustomer" data-backdrop="static">
                                <i class="far fa-edit"></i>
                            </a>
                            <a href="#" class="action-item text-danger hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#deletecustomer" data-backdrop="static">
                                <i class="far fa-trash-alt"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>


            <!--company 1-->

            <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
                <div class="card hover-shadow">
                    <div class="card-body d-flex justify-content-between">
                        <a href="#"
                           class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                           data-bw="3px">
                            <img alt="Image placeholder"
                                 src="/static/customer/img/sampath.jpg">
                        </a>
                        <div class="details vertical-middle">
                            <span class="companyname"><b>Sampath Bank</b></span><br>
                            <span class="email">dulana.r@sampath.lk</span><br>
                            <span class="contact">0112448888</span><br>
                            <span class="badge badge-success mt-3">Co-Operative Customer</span>
                            <span class="badge badge-success mt-3"></span>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="actions d-flex justify-content-between px-4">
                            <a href="#" class="action-item hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#editco_operativecustomer" data-backdrop="static">
                                <i class="far fa-edit"></i>
                            </a>
                            <a href="#" class="action-item text-danger hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#deleteco_operativecustomer" data-backdrop="static">
                                <i class="far fa-trash-alt"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>


            <!--member 3-->

            <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
                <div class="card hover-shadow">
                    <div class="card-body d-flex justify-content-between">
                        <a href="#"
                           class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                           data-bw="3px">
                            <img alt="Image placeholder"
                                 src="/static/customer/img/dinushi.jpg">
                        </a>
                        <div class="details vertical-middle">
                            <span class="customername"><b>N P Dinushi De Silva</b></span><br>
                            <span class="email">dedinushi1995@gmail.com</span><br>
                            <span class="contact">0713561299</span><br>
                            <span class="badge badge-success mt-3">Private Customer</span>
                            <span class="badge badge-success mt-3"></span>
                        </div>
                    </div>
                    <div class="card-footer">
                        <div class="actions d-flex justify-content-between px-4">
                            <a href="#" class="action-item hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#editcustomer" data-backdrop="static">
                                <i class="far fa-edit"></i>
                            </a>
                            <a href="#" class="action-item text-danger hover-translate-y-n3"
                               data-toggle="modal"
                               data-target="#deletecustomer" data-backdrop="static">
                                <i class="far fa-trash-alt"></i>
                            </a>
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



            <!-- Edit Customer-->

            <div id="editcustomer" class="modal fade" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-md" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Customer Details</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form:form method="POST" action="/app/customer/" modelAttribute="customer"
                                   enctype="multipart/form-data">
                            <div class="modal-body">
                                <input type="hidden" name="id">
                                <!--<div class="form-group">
                                    <input type="file" name="image" accept="image/*">
                                </div> -->
                                <div class="form-group">
                                    <input name="name" id="Name" placeholder="Customer Name*"
                                           type="text"
                                           class="form-control">
                                </div>
                                <div class="form-group">
                                    <input name="Email" id="Email" placeholder="Customer Email*"
                                           type="text"
                                           class="form-control">
                                </div>
                                <div class="form-group">
                                    <input name="MobileNumber*" id="MobileNumber"
                                           type="Number" class="form-control"
                                           placeholder="Mobile Number*">
                                </div>
                                <div>
                                  <select class="selectpicker form-control" name="type.id"
                                          data-hide-disabled="true"
                                          data-live-search="true"
                                          title="Status*">
                                        <option value="1">Private Customer</option>
                                        <option value="2">Co-Operative Customer</option>
                                    </select>
                                    <br></br>

                                    <span class="mandatory-warn">*These fields are mandatory</span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="save1" type="button" class="btn btn-primary btn-round btn-sm">Save
                                </button>
                                <button type="reset" class="btn btn-danger btn-round btn-sm"
                                        data-dismiss="modal">Cancel
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>


            <!-- Edit Co-Operative Customer-->

            <div id="editco_operativecustomer" class="modal fade" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-md" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Company Details</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form:form method="POST" action="/app/customer/" modelAttribute="customer"
                                   enctype="multipart/form-data">
                            <div class="modal-body">
                                <input type="hidden" name="id">
                                <!--<div class="form-group">
                                    <input type="file" name="image" accept="image/*">
                                </div> -->
                                </div>
                                <div class="form-group">
                                    <input name="Email" id="CEmail" placeholder="Company Email*"
                                           type="text"
                                           class="form-control">
                                </div>
                                <div class="form-group">
                                    <input name="CNumber*" id="CNumber"
                                           type="Number" class="form-control"
                                           placeholder="Company Number*">
                                </div>

                                <div class="form-group">
                                    <input name="CNumber1*" id="CNumber11"
                                           type="Number" class="form-control"
                                           placeholder="Contact Number*">
                                </div>
                                <div>
                                  <select class="selectpicker form-control" name="type.id"
                                          data-hide-disabled="true"
                                          data-live-search="true"
                                          title="Type*">
                                        <option value="1">Private Customer</option>
                                        <option value="2">Co-Operative Customer</option>
                                    </select>
                                    <br></br>

                                    <span class="mandatory-warn">*These fields are mandatory</span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="save2" type="button" class="btn btn-primary btn-round btn-sm">Save
                                </button>
                                <button type="reset" class="btn btn-danger btn-round btn-sm"
                                        data-dismiss="modal">Cancel
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>



            <!--delete customer-->

            <div id="deletecustomer" class="modal fade" tabindex="-1" role="dialog"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete Customer</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Are you sure you want to delete this Customer ?
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


                <!--delete Co-Operative Customer-->

                <div id="deleteco_operativecustomer" class="modal fade" tabindex="-1" role="dialog"
                     aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Delete Co-Operative Customer</h5>
                                <button type="button" class="close" data-dismiss="modal"
                                        aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Are you sure you want to delete this Co-Operative Customer ?
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

    <!--Search Customer-->

    <div id="customersearch" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Search Customer</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form:form method="POST" action="/app/customer/" modelAttribute="customer"
                           enctype="multipart/form-data">
                <div class="modal-body">
                    <input type="hidden" name="id">
                    <!--<div class="form-group">
                        <input type="file" name="image" accept="image/*">
                    </div> -->
                </div>
                <div class="form-group">
                    <input name="name" id="cname"
                           type="Number" class="form-control"
                           placeholder="Customer or Company Name*">
                </div>

                <div class="form-group">
                    <input name="id*" id="id"
                           type="text" class="form-control"
                           placeholder="ID Number*">
                </div>
            </div>
            <div class="modal-footer">
                <button id="Search" type="button" class="btn btn-primary btn-round btn-sm">Search
                </button>
                <button type="reset" class="btn btn-danger btn-round btn-sm"
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
    <script src="/static/customer/js/customer.js"></script>
    </body>
    </html>
</compress:html>